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
import model.entity.Publisher;

/**
 * Implementation of {@link PublisherDAO} interface
 */
public class PublisherDAOImpl implements PublisherDAO {
	private static PublisherDAO instance;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(PublisherDAOImpl.class.getName());
	}

	/**
	 * Protects constructor to deny direct instantiation
	 */
	private PublisherDAOImpl() {
	}

	/**
	 * Method to get instance of {@link PublisherDAO}
	 * @return instance of {@link PublisherDAO}
	 */
	public static synchronized PublisherDAO getInstance() {
		if (instance == null) {
			instance = new PublisherDAOImpl();
		}
		return instance;
	}

	/**
	 * Retrieves {@link Publisher} by id from data base
	 * @param id given {@link Publisher} id
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public Publisher getPublisher(long id) throws DBException {
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
			logger.error("Can`t get publisher", e);
			throw new DBException("Can`t get publisher", e);
		}
		return publisher;
	}

	/**
	 * Retrieves all {@link Publisher}s from data base
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<Publisher> getAllPublishers() throws DBException {
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
			logger.error("Can`t get all publishers", e);
			throw new DBException("Can`t get all publishers", e);
		}
		return publishers;
	}

	/**
	 * Adds given {@link Publisher} to data base
	 * @param publisher given {@link Publisher}
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void addPublisher(Publisher publisher) throws DBException {
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
			logger.error("Can`t create new publisher", e);
			throw new DBException("Can`t create new publisher", e);
		}
	}

	/**
	 * Updates given {@link Publisher} in data base
	 * @param publisher given {@link Publisher}
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void updatePublisher(Publisher publisher) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_PUBLISHER)) {
			int k = 1;
			ps.setString(k++, publisher.getName());
			ps.setLong(k++, publisher.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can`t update publisher", e);
			throw new DBException("Can`t update publisher", e);
		}
	}

}
