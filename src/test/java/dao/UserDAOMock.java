package dao;

import java.util.ArrayList;
import java.util.List;

import Exception.DBException;
import model.DAO.UserDAO;
import model.entity.User;
import model.entity.UserType;

public class UserDAOMock implements UserDAO{
	private List<User> users;
	private List<UserType> userTypes;
	
	public UserDAOMock() {
		userTypes = new ArrayList<UserType>();
		UserType ut = new UserType();
		ut.setId(1);
		ut.setType("admin");
		userTypes.add(ut);
		ut = new UserType();
		ut.setId(2);
		ut.setType("librarian");
		userTypes.add(ut);
		ut = new UserType();
		ut.setId(3);
		ut.setType("user");
		userTypes.add(ut);
		
		users = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setLogin("1");
		user.setFirstName("1");
		user.setLastName("1");
		user.setIsBlocked(false);
		user.setPenalty(0);
		user.setUserType(userTypes.get(1));
		users.add(user);
		user = new User();
		user.setId(2);
		user.setLogin("2");
		user.setFirstName("2");
		user.setLastName("2");
		user.setIsBlocked(false);
		user.setPenalty(0);
		user.setUserType(userTypes.get(2));
		users.add(user);
	}

	@Override
	public User getUserByLogin(String login) throws DBException {
		return users.stream().filter(e -> e.getLogin().equals(login)).findFirst().orElse(null);
	}

	@Override
	public List<User> getAllUsers() throws DBException {
		return users;
	}

	@Override
	public void updateUser(User user) throws DBException {
		User userStored = users.stream().filter(e -> e.getId() == user.getId()).findFirst().orElse(user);
		if (userStored != null) {
			//copy values
		}
	}

	@Override
	public void addUser(User user) throws DBException {
		users.add(user);		
	}

	@Override
	public List<UserType> getAllUserTypes() throws DBException {
		return userTypes;
	}

	@Override
	public List<User> getAllUsersWithOpenOrders() throws DBException {
		return null;
	}

	@Override
	public User getUserById(Long userId) throws DBException {
		return users.stream().filter(e -> e.getId() == userId).findFirst().orElse(null);
	}

}
