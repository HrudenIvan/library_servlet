package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import controller.util.UserNavigation;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;
import util.Password;

public class AddUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		HttpSession session = request.getSession();
		if (password.isBlank() || !password.equals(password1)) {
			session.setAttribute("errorRegistrationMessage", "Both passwords must be equal");
			return "register.jsp";
		}
		User user = new User();
		user.setLogin(request.getParameter("login"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		UserType ut = new UserType();
		ut.setId(3);
		ut.setType("user");
		user.setSalt(Password.generateSalt());
		user.setPassword(Password.hash(password, user.getSalt()));
		user.setUserType(ut);
		UserDAO userDAO = UserDAOImpl.getInstance();
		userDAO.addUser(user);
		session.removeAttribute("errorRegistrationMessage");
		session.setAttribute("currentUserId", user.getId());
		session.setAttribute("currentUserType", user.getUserType().getType());
		session.setAttribute("navigation", UserNavigation.getInstance());
		return "main?action=getAllBooks";
	}

}
