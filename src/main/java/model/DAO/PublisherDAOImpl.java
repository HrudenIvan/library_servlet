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
import model.entity.Publisher;

public class PublisherDAOImpl implements PublisherDAO {
	private static PublisherDAO instance;

	private PublisherDAOImpl() {
	}

	public static synchronized PublisherDAO getInstance() {
		if (instance == null) {
			instance = new PublisherDAOImpl();
		}
		return instance;
	}

	@Override
	public Publisher getPublisher(long id) {
		Publisher publisher = new Publisher();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_PUBLISHER_BY_ID)) {
			ps.setLong(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				int k = 1;
				publisher.setId(Long.valueOf(rs.getLong(k++)));
				publisher.setName(rs.getString(k++));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisher;
	}

	@Override
	public List<Publisher> getAllPublishers() {
		List<Publisher> publishers = new ArrayList<Publisher>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_PUBLISHERS)) {
			while (rs.next()) {
				Publisher publisher = new Publisher();
				int k = 1;
				publisher.setId(Long.valueOf(rs.getLong(k++)));
				publisher.setName(rs.getString(k++));
				publishers.add(publisher);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publishers;
	}

	@Override
	public void addPublisher(Publisher publisher) {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.ADD_PUBLISHER,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, publisher.getName());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				publisher.setId(Long.valueOf(rs.getLong(1)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_PUBLISHER)) {
			int k = 1;
			ps.setString(k++, publisher.getName());
			ps.setLong(k++, publisher.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
