package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;
import util.Password;
import util.Validator;

/**
 * Command for updating {@link User}
 */
public class UpdateUserCommand implements Command {

	/**
	 * Method for handling {@link User} updating. If updated user is not valid, then forwards to update user page with error messages,
	 * otherwise redirects to all users page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Long uId = Long.valueOf(request.getParameter("uId"));
		User user = new User();
		user.setId(uId);
		user.setLogin(request.getParameter("login").trim());
		user.setFirstName(request.getParameter("firstName").trim());
		user.setLastName(request.getParameter("lastName").trim());
		UserType ut = new UserType();
		ut.setId(Integer.valueOf(request.getParameter("tId")));
		user.setUserType(ut);
		String penalty = request.getParameter("penalty");
		if (penalty.isEmpty()) {
			user.setPenalty(0);
		} else {
			user.setPenalty(Double.parseDouble(penalty));
		}
		user.setIsBlocked(Boolean.parseBoolean(request.getParameter("isBlocked")));
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateUser(request, user, errors) || 
				!Validator.validatePassword(request, password, password1, errors)) {
			request.setAttribute("errors", errors);
			request.setAttribute("user", user);
			request.setAttribute("userTypes", userDAO.getAllUserTypes());

			request.getRequestDispatcher("updateUser.jsp").forward(request, response);
			return;
		}
		
		user.setSalt(Password.generateSalt());
		user.setPassword(Password.hash(password, user.getSalt()));
		userDAO.updateUser(user);
		
		response.sendRedirect("main?action=getAllUsers");
	}

}
