package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

public class PrepareBookOrderUpdateCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		Long userId = Long.valueOf(request.getParameter("uId"));
		Long bookId = Long.valueOf(request.getParameter("bId"));
		BookOrder bookOrder = orderDAO.getBookOrder(userId, bookId);
		request.setAttribute("bookOrder", bookOrder);

		List<OrderStatus> orderStatuses = orderDAO.getAllOrderStatuses();
		request.setAttribute("orderStatuses", orderStatuses);

		request.getRequestDispatcher("updateBookOrder.jsp").forward(request, response);
	}

}
