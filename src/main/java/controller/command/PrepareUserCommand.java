package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;
import model.entity.UserType;

public class PrepareUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long uId = Long.valueOf(request.getParameter("uId"));
		User user = userDAO.getUserById(uId);
		request.setAttribute("user", user);

		List<UserType> userTypes = userDAO.getAllUserTypes();
		request.setAttribute("userTypes", userTypes);

		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
	}

}
