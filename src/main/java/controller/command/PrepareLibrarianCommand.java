package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class PrepareLibrarianCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getNewBookOrders();
		request.setAttribute("bookOrders", bookOrders);

		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsersWithOpenOrders();
		request.setAttribute("users", users);

		Long currentUserId = (Long) request.getSession().getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(currentUserId);
		request.setAttribute("currentUser", currentUser);

		request.getRequestDispatcher("librarian.jsp").forward(request, response);
	}

}
