package controller.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.DBException;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;

import util.Localizer;
import util.Password;
import util.Util;

/**
 * Login command
 */
public class LoginCommand implements Command {

	/**
	 * Verifies login data. If credentials is wrong, then forwards to login page with error message,
	 * otherwise stores current user data in session and redirects to default command
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		String login = request.getParameter("login");
		UserDAO userDao = UserDAOImpl.getInstance();
		User user = userDao.getUserByLogin(login);
		String password = request.getParameter("password");
		
		if (user == null || !Password.isExpectedPassword(Password.hash(password, user.getSalt()), user.getPassword())) {
			request.setAttribute("errorLoginMessage", Localizer.getString(request, "login.errormessage"));
			request.setAttribute("login", login);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		if ("admin".equals(user.getUserType().getType())) {
			session.setAttribute("bookEditVis", "inline table");
			session.setAttribute("bookOrderVis", "none");
		}
		if ("user".equals(user.getUserType().getType())) {
			session.setAttribute("bookEditVis", "none");
			session.setAttribute("bookOrderVis", "inline table");
		}
		
		session.setAttribute("isBlocked", user.getIsBlocked());
		session.setAttribute("currentUserId", user.getId());
		session.setAttribute("currentUserType", user.getUserType().getType());
		Locale locale = Util.defineLocale(request);
		session.setAttribute("locale", locale);
		
		response.sendRedirect("main?action=default");
	}
	
}
