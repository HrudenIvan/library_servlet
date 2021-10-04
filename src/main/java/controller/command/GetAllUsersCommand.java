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

/**
 * Command to access all {@link User}s page
 */
public class GetAllUsersCommand implements Command {

	/**
	 * Adds list of {@link User}s to request and forwards to all users page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsers();
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("users.jsp").forward(request, response);
	}

}
