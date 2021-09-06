package controller.command;

import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

public class UpdateBookOrderCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
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
		return "main?action=prepareLibrarian";
	}

}
