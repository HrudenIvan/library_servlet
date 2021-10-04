package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.DBException;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;

/**
 * Command to access admin page
 */
public class PrepareAdminCommand implements Command {

	/**
	 * Stores user details in request and forwards to admin page
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
		HttpSession session = request.getSession();
		Long userId = (Long) session.getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(userId);
		request.setAttribute("currentUser", currentUser);

		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

}
