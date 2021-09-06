package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;

public class GetAllUsersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsers();
		request.setAttribute("users", users);
		return "users.jsp";
	}

}
