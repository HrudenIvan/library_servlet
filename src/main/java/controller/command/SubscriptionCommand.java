package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class SubscriptionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long userId = Long.valueOf(request.getParameter("uId"));
		User user = userDAO.getUserById(userId);
		request.setAttribute("user", user);

		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getUserOpenBookOrders(userId);
		request.setAttribute("bookOrders", bookOrders);

		request.getRequestDispatcher("subscription.jsp").forward(request, response);
	}

}
