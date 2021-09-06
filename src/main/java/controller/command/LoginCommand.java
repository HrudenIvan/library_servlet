package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import controller.util.UserNavigation;
import controller.util.AdminNavigation;
import controller.util.LibrarianNavigation;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import util.Password;

public class LoginCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("errorMessage");
		String login = request.getParameter("login");
		UserDAO userDao = UserDAOImpl.getInstance();
		User user = userDao.getUserByLogin(login);
		String password = request.getParameter("password");
		if (user == null || !Password.isExpectedPassword(Password.hash(password, user.getSalt()), user.getPassword())) {
			session.setAttribute("errorLoginMessage", "Invalid login or password");
			return "login.jsp";
		}
		if ("admin".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", AdminNavigation.getInstance());
		}
		if ("librarian".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", LibrarianNavigation.getInstance());
		}
		if ("user".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", UserNavigation.getInstance());
		}
		session.removeAttribute("errorLoginMessage");
		session.setAttribute("currentUserId", user.getId());
		session.setAttribute("currentUserType", user.getUserType().getType());
		return "main?action=default";
	}

}
