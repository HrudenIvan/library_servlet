package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Constants;
import model.PooledConnections;
import model.entity.Author;

public class AuthorDAOImpl implements AuthorDAO {
	private static AuthorDAO instance;
	
	private AuthorDAOImpl() {}
	
	public static synchronized AuthorDAO getInstance() {
		if (instance == null) {
			instance = new AuthorDAOImpl();
		}
		return instance;
	}

	@Override
	public Author getAuthor(long id) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return author;
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = new ArrayList<Author>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_AUTHORS)){
			while (rs.next()) {
				Author author = new Author();
				int k = 1;
				author.setId(rs.getLong(k++));
				author.setFirstName(rs.getString(k++));
				author.setLastName(rs.getString(k++));
				authors.add(author);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authors;
	}

	@Override
	public void addAuthor(Author author) {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.ADD_AUTHOR, PreparedStatement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, author.getFirstName());
			ps.setString(k++, author.getLastName());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				author.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAuthor(Author author) {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_AUTHOR)) {
			int k = 1;
			ps.setString(k++, author.getFirstName());
			ps.setString(k++, author.getLastName());
			ps.setLong(k++, author.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
