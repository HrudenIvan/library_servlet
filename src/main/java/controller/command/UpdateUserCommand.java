package controller.command;

import javax.servlet.http.HttpServletRequest;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;
import util.Password;

public class UpdateUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String password = request.getParameter("password");
		String password1 = request.getParameter("password1");
		Long uId = Long.valueOf(request.getParameter("uId"));
		if (!password.equals(password1)) {
			return "main?action=prepareUser&uId="+uId;
		}
		User user = new User();
		user.setId(uId);
		user.setLogin(request.getParameter("login"));
		user.setFirstName(request.getParameter("firstName"));
		user.setLastName(request.getParameter("lastName"));
		UserType ut = new UserType();
		ut.setId(Integer.valueOf(request.getParameter("tId")));
		user.setUserType(ut);
		user.setSalt(Password.generateSalt());
		user.setPassword(Password.hash(password, user.getSalt()));
		user.setPenalty(Double.parseDouble(request.getParameter("penalty")));
		user.setIsBlocked(Boolean.parseBoolean(request.getParameter("isBlocked")));
		UserDAO userDAO = UserDAOImpl.getInstance();
		userDAO.updateUser(user);
		return "main?action=getAllUsers";
	}

}
