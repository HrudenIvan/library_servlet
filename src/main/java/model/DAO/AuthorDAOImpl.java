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

/**
 * Implementation of {@link AuthorDAO} interface
 */
public class AuthorDAOImpl implements AuthorDAO {
	private static AuthorDAO instance;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(AuthorDAOImpl.class.getName());
	}

	/**
	 * Protects constructor to deny direct instantiation
	 */
	private AuthorDAOImpl() {
	}

	/**
	 * Method to get instance of {@link AuthorDAO}
	 * @return instance of {@link AuthorDAO}
	 */
	public static synchronized AuthorDAO getInstance() {
		if (instance == null) {
			instance = new AuthorDAOImpl();
		}
		return instance;
	}

	/**
	 * Retrieve {@link Author} by id from data base
	 * @param id author id
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public Author getAuthor(long id) throws DBException {
		Author author = new Author();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_AUTHOR_BY_ID)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				int k = 1;
				author.setId(rs.getLong(k++));
				author.setFirstName(rs.getString(k++));
				author.setLastName(rs.getString(k++));
			}
		} catch (SQLException e) {
			logger.error("Can`t get author", e);
			throw new DBException("Can`t get author", e);
		}
		return author;
	}

	/**
	 * Retrieve all {@link Author}s from data base
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<Author> getAllAuthors() throws DBException {
		List<Author> authors = new ArrayList<Author>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_AUTHORS)) {
			while (rs.next()) {
				Author author = new Author();
				int k = 1;
				author.setId(rs.getLong(k++));
				author.setFirstName(rs.getString(k++));
				author.setLastName(rs.getString(k++));
				authors.add(author);
			}
		} catch (SQLException e) {
			logger.error("Can`t get all authors", e);
			throw new DBException("Can`t get all authors", e);
		}
		return authors;
	}

	/**
	 * Adds given {@link Author} to data base
	 * @param author {@link Author} to be added
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void addAuthor(Author author) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.ADD_AUTHOR,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, author.getFirstName());
			ps.setString(k++, author.getLastName());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				author.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.error("Can`t create author", e);
			throw new DBException("Can`t create author", e);
		}
	}

	/**
	 * Updates given {@link Author} in data base
	 * @param author {@link Author} to be updated
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void updateAuthor(Author author) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_AUTHOR)) {
			int k = 1;
			ps.setString(k++, author.getFirstName());
			ps.setString(k++, author.getLastName());
			ps.setLong(k++, author.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can`t update author", e);
			throw new DBException("Can`t update author", e);
		}
	}

}
