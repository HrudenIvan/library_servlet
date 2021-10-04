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
import model.entity.UserType;

/**
 * Command to access {@link User} update page
 */
public class PrepareUserCommand implements Command {

	/**
	 * Stores needed {@link User}, list of {@link UserType}s in request and forwards to update user page
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
		Long uId = Long.valueOf(request.getParameter("uId"));
		User user = userDAO.getUserById(uId);
		request.setAttribute("user", user);

		List<UserType> userTypes = userDAO.getAllUserTypes();
		request.setAttribute("userTypes", userTypes);

		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}

}
