package model.DAO;

import java.util.List;
import model.entity.User;
import model.entity.UserType;

public interface UserDAO {
	User getUserByLogin(String login);

	List<User> getAllUsers();

	void updateUser(User user);

	void addUser(User user);
	
	List<UserType> getAllUserTypes();

	List<User> getAllUsersWithOpenOrders();

	User getUserById(Long userId);
}
