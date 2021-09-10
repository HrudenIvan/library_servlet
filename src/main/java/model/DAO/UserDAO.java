package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.User;
import model.entity.UserType;

public interface UserDAO {
	User getUserByLogin(String login) throws DBException;

	List<User> getAllUsers() throws DBException;

	void updateUser(User user) throws DBException;

	void addUser(User user) throws DBException;

	List<UserType> getAllUserTypes() throws DBException;

	List<User> getAllUsersWithOpenOrders() throws DBException;

	User getUserById(Long userId) throws DBException;
}
