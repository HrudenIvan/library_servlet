package controller.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

/**
 * Command to access {@link BookOrder} update page
 */
public class PrepareBookOrderUpdateCommand implements Command {

	/**
	 * Stores {@link BookOrder}, list of {@link OrderStatus}es in request and forwards to update book order page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		Long userId = Long.valueOf(request.getParameter("uId"));
		Long bookId = Long.valueOf(request.getParameter("bId"));
		BookOrder bookOrder = orderDAO.getBookOrder(userId, bookId);
		if (bookOrder.getOrderStatus().getId() == 2) {
			bookOrder.setOpenDate(LocalDate.now());
			bookOrder.setCloseDate(LocalDate.now());
		}
		request.setAttribute("bookOrder", bookOrder);

		List<OrderStatus> orderStatuses = orderDAO.getAllOrderStatuses();
		request.setAttribute("orderStatuses", orderStatuses);

		request.getRequestDispatcher("updateBookOrder.jsp").forward(request, response);
	}

}
