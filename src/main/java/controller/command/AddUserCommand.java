package controller.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.DBException;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;

import util.Password;
import util.Util;
import util.Validator;

/**
 * Command for adding new {@link User}
 */
public class AddUserCommand implements Command {

	/**
	 * Method for handling {@link User} adding. If new user not valid, then forwards to add user page with error messages,
	 * otherwise configures session with new user and redirects to all books page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		User user = new User();
		user.setLogin(request.getParameter("login"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		UserType ut = new UserType();
		ut.setId(3);
		ut.setType("user");
		user.setUserType(ut);
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateUser(request, user, errors) || 
				!Validator.validatePassword(request, password, password1, errors)) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);

			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		user.setSalt(Password.generateSalt());
		user.setPassword(Password.hash(password, user.getSalt()));
		UserDAO userDAO = UserDAOImpl.getInstance();
		userDAO.addUser(user);

		HttpSession session = request.getSession();
		session.setAttribute("bookEditVis", "none");
		session.setAttribute("bookOrderVis", "inline table");
		session.setAttribute("currentUserId", user.getId());
		session.setAttribute("currentUserType", user.getUserType().getType());
		Locale locale = Util.defineLocale(request);
		session.setAttribute("locale", locale);

		response.sendRedirect("main?action=getAllBooks");
	}

}
