package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class BookDAOImpl implements BookDAO {
	private static BookDAO instance;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(BookDAOImpl.class.getName());
	}

	public static synchronized BookDAO getInstance() {
		if (instance == null) {
			instance = new BookDAOImpl();
		}
		return instance;
	}

	@Override
	public List<Book> getAllBooks() throws DBException {
		List<Book> books = new ArrayList<Book>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement statement = con.createStatement();
				ResultSet rs = statement.executeQuery(Constants.GET_ALL_BOOKS)) {
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
		} catch (SQLException e) {
			logger.error("Can`t get all books", e);
			throw new DBException("Can`t get all books", e);
		}
		return books;
	}

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

	@Override
	public List<Book> findBooksByTitle(String title) throws DBException {
		List<Book> books = new ArrayList<Book>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_BOOKS_BY_TITLE)) {
			ps.setString(1, "%" + title + "%");
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
			logger.error("Can`t finde book by title", e);
			throw new DBException("Can`t finde book by title", e);
		}
		return books;
	}

}
