package controller.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

import util.Validator;

/**
 * Command for updating {@link BookOrder}
 */
public class UpdateBookOrderCommand implements Command {

	/**
	 * Method for handling {@link BookOrder} updating. If updated BookOrder not valid, then forwards to update book order page with error messages,
	 * otherwise redirects to librarian page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		BookOrder bookOrder = new BookOrder();
		bookOrder.setBookId(Long.valueOf(request.getParameter("bId")));
		bookOrder.setUserId(Long.valueOf(request.getParameter("uId")));
		bookOrder.setOrderDate(LocalDateTime.parse(request.getParameter("orderDate")));
		bookOrder.setBookTitle(request.getParameter("title").trim());
		bookOrder.setOrderType(request.getParameter("orderType"));
		bookOrder.setPenalty(Double.parseDouble(request.getParameter("penalty")));
		if (!request.getParameter("openDate").isEmpty()) {
			bookOrder.setOpenDate(LocalDate.parse(request.getParameter("openDate")));
		}
		if (!request.getParameter("closeDate").isEmpty()) {
			bookOrder.setCloseDate(LocalDate.parse(request.getParameter("closeDate")));
		}
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(Integer.parseInt(request.getParameter("osId")));
		bookOrder.setOrderStatus(orderStatus);
		
		LocalDate oldCloseDate = null;
		if (!request.getParameter("oldCloseDate").isEmpty()) {
			oldCloseDate = LocalDate.parse(request.getParameter("oldCloseDate"));
		}
		int oldOsId = Integer.parseInt(request.getParameter("oldOsId"));
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateAndUpdateBookOrder(request, bookOrder, oldOsId, oldCloseDate, errors)) {
			List<OrderStatus> orderStatuses = orderDAO.getAllOrderStatuses();
			request.setAttribute("orderStatuses", orderStatuses);
			request.setAttribute("errors", errors);
			request.setAttribute("bookOrder", bookOrder);
			
			request.getRequestDispatcher("updateBookOrder.jsp").forward(request, response);
			return;
		}
		
		orderDAO.updateBookOrder(bookOrder);
		
		response.sendRedirect("main?action=prepareLibrarian");
	}

}
