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
import model.PooledConnections;
import model.entity.*;
import model.*;

/**
 * Implementation of {@link UserDAO} interface
 */
public class UserDAOImpl implements UserDAO {
	private static UserDAO instance;
	private static final Logger logger;

	static {
		logger = LogManager.getLogger(UserDAOImpl.class.getName());
	}

	/**
	 * Protects constructor to deny direct instantiation
	 */
	private UserDAOImpl() {
	}

	/**
	 * Method to get instance of {@link UserDAO}
	 * @return instance of {@link UserDAO}
	 */
	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	/**
	 * Retrieves {@link User} by login from data base
	 * @param login {@link User} login
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public User getUserByLogin(String login) throws DBException {
		User user = null;
		if (login == null)
			return null;
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_USER_BY_LOGIN)) {
			ps.setString(1, login);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					user = new User();
					int k = 1;
					user.setId(rs.getLong(k++));
					UserType userType = new UserType();
					userType.setId(rs.getInt(k++));
					userType.setType(rs.getString(k++));
					user.setUserType(userType);
					user.setPassword(rs.getBytes(k++));
					user.setSalt(rs.getBytes(k++));
					user.setIsBlocked(rs.getBoolean(k++));
				}
			}
		} catch (SQLException e) {
			logger.error("Can`t get user by login", e);
			throw new DBException("Can`t get user by login", e);
		}
		return user;
	}

	/**
	 * Retrieves all {@link User}s from data base
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<User> getAllUsers() throws DBException {
		List<User> users = new ArrayList<>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_USERS)) {
			while (rs.next()) {
				User user = new User();
				int k = 1;
				user.setId(rs.getLong(k++));
				user.setLogin(rs.getString(k++));
				user.setFirstName(rs.getString(k++));
				user.setLastName(rs.getString(k++));
				UserType userType = new UserType();
				userType.setId(rs.getInt(k++));
				userType.setType(rs.getString(k++));
				user.setUserType(userType);
				user.setPenalty(rs.getDouble(k++));
				user.setIsBlocked(rs.getBoolean(k++));
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Can`t get all users", e);
			throw new DBException("Can`t get all users", e);
		}
		return users;
	}

	/**
	 * Updates given {@link User} in data base
	 * @param user given {@link User}
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void updateUser(User user) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.UPDATE_USER)) {
			int k = 1;
			ps.setString(k++, user.getLogin());
			ps.setString(k++, user.getFirstName());
			ps.setString(k++, user.getLastName());
			ps.setInt(k++, user.getUserType().getId());
			ps.setBytes(k++, user.getPassword());
			ps.setBytes(k++, user.getSalt());
			ps.setBoolean(k++, user.getIsBlocked());
			ps.setLong(k++, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Can`t update user", e);
			throw new DBException("Can`t update user", e);
		}
	}

	/**
	 * Adds given {@link User} to data base
	 * @param user given {@link User}
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public void addUser(User user) throws DBException {
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.ADD_USER,
						PreparedStatement.RETURN_GENERATED_KEYS)) {
			int k = 1;
			ps.setString(k++, user.getLogin());
			ps.setBytes(k++, user.getPassword());
			ps.setBytes(k++, user.getSalt());
			ps.setString(k++, user.getFirstName());
			ps.setString(k++, user.getLastName());
			ps.executeUpdate();
			try (ResultSet rs = ps.getGeneratedKeys()) {
				rs.next();
				user.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			logger.error("Can`t create new user", e);
			throw new DBException("Can`t create new user", e);
		}
	}

	/**
	 * Retrieves all {@link UserType}s from data base
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<UserType> getAllUserTypes() throws DBException {
		List<UserType> userTypes = new ArrayList<UserType>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_USERTYPES)) {
			while (rs.next()) {
				UserType userType = new UserType();
				int k = 1;
				userType.setId(rs.getInt(k++));
				userType.setType(rs.getString(k++));
				userTypes.add(userType);
			}
		} catch (SQLException e) {
			logger.error("Can`t get all user types", e);
			throw new DBException("Can`t get all user types", e);
		}
		return userTypes;
	}

	/**
	 * Retrieves all {@link User}s with open BookOrders from data base
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public List<User> getAllUsersWithOpenOrders() throws DBException {
		List<User> users = new ArrayList<User>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_USER_WITH_OPEN_ORDERS)) {
			while (rs.next()) {
				User user = new User();
				int k = 1;
				user.setId(rs.getLong(k++));
				user.setLogin(rs.getString(k++));
				user.setFirstName(rs.getString(k++));
				user.setLastName(rs.getString(k++));
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error("Can`t get all user with open orders", e);
			throw new DBException("Can`t get all user with open orders", e);
		}
		return users;
	}

	/**
	 * Retrieves {@link User} by id from data base
	 * @param userId {@link User} id
	 * @throws DBException in case of {@link SQLException}
	 */
	@Override
	public User getUserById(Long userId) throws DBException {
		User user = new User();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_USER_BY_ID)) {
			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				rs.next();
				int k = 1;
				user.setId(rs.getLong(k++));
				user.setLogin(rs.getString(k++));
				user.setFirstName(rs.getString(k++));
				user.setLastName(rs.getString(k++));
				user.setPenalty(rs.getDouble(k++));
				user.setIsBlocked(rs.getBoolean(k++));
				UserType ut = new UserType();
				ut.setId(rs.getInt(k++));
				ut.setType(rs.getString(k++));
				user.setUserType(ut);
			}
		} catch (SQLException e) {
			logger.error("Can`t get user by id", e);
			throw new DBException("Can`t get user by id", e);
		}

		return user;
	}

}
