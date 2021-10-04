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

/**
 * Command to access subscription page
 */
public class SubscriptionCommand implements Command {

	/**
	 * Stores needed {@link User}, his list of {@link BookOrder}s in request and forwards to subscription page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
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
