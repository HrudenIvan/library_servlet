package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.DBException;

import model.Constants;
import model.PooledConnections;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;

/**
 * Implementation of {@link BookDAO} interface
 */
public class BookDAOImpl implements BookDAO {
	private static BookDAO instance;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(BookDAOImpl.class.getName());
	}

	/**
	 * Protects constructor to deny direct instantiation
	 */
	private BookDAOImpl() {}
	
	/**
	 * Method to get instance of {@link BookDAO}
	 * @return instance of {@link BookDAO}
	 */
	public static synchronized BookDAO getInstance() {
		if (instance == null) {
			instance = new BookDAOImpl();
		}
		return instance;
	}

	/**
	 * Retrieves all {@link Book}s from data base according to pagination parameters
	 * @param page page number
	 * @param BOOKS_PER_PAGE quantity of books per page
	 * @param sort column name for sort by
	 * @param order sorting direction
	 * @param title book title
	 * @param aLastname author last name
	 * @param aFirstname author first name
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<Book> getAllBooks(int page,int BOOKS_PER_PAGE, String sort, String order, String title, String aLastname, String aFirstname)
			throws DBException {
		String query = "SELECT b.id AS bid, b.title, b.available, b.total, a.id AS aid, a.first_name, a.last_name, p.id AS pid, p.name, b.release_date "
				+ "FROM book b " + "INNER JOIN author a ON b.author_id = a.id "
				+ "INNER JOIN publisher p ON b.publisher_id = p.id "
				+ "WHERE b.available >0 AND b.title LIKE( ? ) AND a.first_name LIKE( ? ) AND a.last_name LIKE( ? ) "
				+ "ORDER BY ";
		query += sort + " " + order + " " + "LIMIT " + BOOKS_PER_PAGE + " " + " OFFSET " + page * BOOKS_PER_PAGE;
		List<Book> books = new ArrayList<Book>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(query)) {
			int j = 1;
			ps.setString(j++, "%" + title + "%");
			ps.setString(j++, "%" + aFirstname + "%");
			ps.setString(j++, "%" + aLastname + "%");
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Book book = new Book();
					int k = 1;
					book.setId(rs.getLong(k++));
					book.setTitle(rs.getString(k++));
					book.setAvailable(rs.getInt(k++));
					book.setQuantity(rs.getInt(k++));
					Author author = new Author();
					author.setId(rs.getLong(k++));
					author.setFirstName(rs.getString(k++));
					author.setLastName(rs.getString(k++));
					book.setAuthor(author);
					Publisher publisher = new Publisher();
					publisher.setId(rs.getLong(k++));
					publisher.setName(rs.getString(k++));
					book.setPublisher(publisher);
					book.setReleaseDate(rs.getInt(k++));
					books.add(book);
				}
			}
		} catch (SQLException e) {
			logger.error("Can`t get all books", e);
			throw new DBException("Can`t get all books", e);
		}
		return books;
	}
	
	/**
	 * Retrieve {@link Book} by id from data base
	 * @param id book id
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public Book getBook(long id) throws DBException {
		Book book = null;
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_BOOK_BY_ID)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					book = new Book();
					int k = 1;
					book.setId(rs.getLong(k++));
					book.setTitle(rs.getString(k++));
					book.setAvailable(rs.getInt(k++));
					book.setQuantity(rs.getInt(k++));
					Author author = new Author();
					author.setId(rs.getInt(k++));
					author.setFirstName(rs.getString(k++));
					author.setLastName(rs.getString(k++));
					book.setAuthor(author);
					Publisher publisher = new Publisher();
					publisher.setId(rs.getLong(k++));
					publisher.setName(rs.getString(k++));
					book.setPublisher(publisher);
					book.setReleaseDate(rs.getInt(k++));
				}
			}
		} catch (SQLException e) {
			logger.error("Can`t get book", e);
			throw new DBException("Can`t get book", e);
		}
		return book;
	}

	/**
	 * Updates given {@link Book} in data base
	 * @param book {@link Book} to be updated
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void updateBook(Book book) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_BOOK)) {
			int k = 1;
			ps.setString(k++, book.getTitle());
			ps.setInt(k++, book.getAvailable());
			ps.setInt(k++, book.getQuantity());
			ps.setLong(k++, book.getAuthor().getId());
			ps.setLong(k++, book.getPublisher().getId());
			ps.setInt(k++, book.getReleaseDate());
			ps.setLong(k++, book.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can`t update book", e);
			throw new DBException("Can`t update book", e);
		}

	}

	/**
	 * Adds given {@link Book} to data base
	 * @param book {@link Book} to be added
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void addBook(Book book) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.ADD_BOOK,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, book.getTitle());
			ps.setInt(k++, book.getQuantity());
			ps.setInt(k++, book.getAvailable());
			ps.setLong(k++, book.getAuthor().getId());
			ps.setLong(k++, book.getPublisher().getId());
			ps.setInt(k++, book.getReleaseDate());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				book.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.error("Can`t create book", e);
			throw new DBException("Can`t create book", e);
		}
	}

	/**
	 * Returns count of {@link Book} in data base which satisfies pagination parameters
	 * @param sort column name for sort by
	 * @param order sorting direction
	 * @param title book title
	 * @param aLastname author last name
	 * @param aFirstname author first name
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public int booksCount(String sort, String order, String title, String aLastname, String aFirstname)
			throws DBException {
		int result = 10;
		String query = 
				  "SELECT COUNT(*) AS count "
				+ "FROM book b "
				+ "INNER JOIN author a ON b.author_id = a.id "
				+ "INNER JOIN publisher p ON b.publisher_id = p.id "
				+ "WHERE b.available >0 AND b.title LIKE( ? ) AND a.first_name LIKE( ? ) AND a.last_name LIKE( ? ) "
				+ "ORDER BY ";
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(query + sort + " " + order)) {
			int k = 1;
			ps.setString(k++, "%" + title + "%");
			ps.setString(k++, "%" + aFirstname + "%");
			ps.setString(k++, "%" + aLastname + "%"); 
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = rs.getInt("count");
				}
			}
		} catch (SQLException e) {
			logger.error("Can`t get books count", e);
			throw new DBException("Can`t get books count", e);
		}
		return result;
	}

}
