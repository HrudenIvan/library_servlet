package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;

import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.User;

public class PrepareAdminCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long userId = (Long) request.getSession().getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(userId);
		request.setAttribute("currentUser", currentUser);

		request.getRequestDispatcher("admin.jsp").forward(request, response);

	}

}
