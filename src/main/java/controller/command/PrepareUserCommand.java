package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;

public class PrepareUserCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long uId = Long.valueOf(request.getParameter("uId"));
		User user = userDAO.getUserById(uId);
		request.setAttribute("user", user);
		
		List<UserType> userTypes = userDAO.getAllUserTypes();
		request.setAttribute("userTypes", userTypes);
		
		return "updateUser.jsp";
	}

}
