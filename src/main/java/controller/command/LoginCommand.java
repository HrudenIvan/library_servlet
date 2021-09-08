package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		UserDAO userDao = UserDAOImpl.getInstance();
		User user = userDao.getUserByLogin(login);
		String password = request.getParameter("password");
		
		if (user == null || !Password.isExpectedPassword(Password.hash(password, user.getSalt()), user.getPassword())) {
			request.setAttribute("errorLoginMessage", "Invalid login or password");
			request.setAttribute("login", login);
			
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		HttpSession session = request.getSession();
		if ("admin".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", AdminNavigation.getInstance());
		}
		if ("librarian".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", LibrarianNavigation.getInstance());
		}
		if ("user".equals(user.getUserType().getType())) {
			session.setAttribute("navigation", UserNavigation.getInstance());
		}
		
		session.setAttribute("currentUserId", user.getId());
		session.setAttribute("currentUserType", user.getUserType().getType());
		
		response.sendRedirect("main?action=default");
	}

}
