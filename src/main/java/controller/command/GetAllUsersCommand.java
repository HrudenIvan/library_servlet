package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;

public class GetAllUsersCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsers();
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("users.jsp").forward(request, response);
	}

}
