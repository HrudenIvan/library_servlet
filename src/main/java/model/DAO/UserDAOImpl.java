package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.PooledConnections;
import model.entity.*;
import model.*;

public class UserDAOImpl implements UserDAO {
	private static UserDAO instance;

	private UserDAOImpl() {
	}

	public static synchronized UserDAO getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}

	@Override
	public User getUserByLogin(String login) {
		User user = null;
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
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAllUsers() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void updateUser(User user) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void addUser(User user) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<UserType> getAllUserTypes() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userTypes;
	}

	@Override
	public List<User> getAllUsersWithOpenOrders() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User getUserById(Long userId) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

}
