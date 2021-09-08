package controller.command;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

public class UpdateBookOrderCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BookOrder bookOrder = new BookOrder();
		bookOrder.setBookId(Long.valueOf(request.getParameter("bId")));
		bookOrder.setUserId(Long.valueOf(request.getParameter("uId")));
		if (!request.getParameter("openDate").isBlank()) {
			bookOrder.setOpenDate(LocalDate.parse(request.getParameter("openDate")));
		}
		if (!request.getParameter("closeDate").isBlank()) {
			bookOrder.setCloseDate(LocalDate.parse(request.getParameter("closeDate")));
		}
		OrderStatus orderStatus = new OrderStatus();
		orderStatus.setId(Integer.parseInt(request.getParameter("osId")));
		bookOrder.setOrderStatus(orderStatus);
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		orderDAO.updateBookOrder(bookOrder);
		
		response.sendRedirect("main?action=prepareLibrarian");
	}

}
