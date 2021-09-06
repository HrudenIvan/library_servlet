package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;
import model.entity.OrderStatus;

public class PrepareBookOrderUpdateCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		Long userId = Long.valueOf(request.getParameter("uId"));
		Long bookId = Long.valueOf(request.getParameter("bId"));
		BookOrder bookOrder = orderDAO.getBookOrder(userId, bookId);
		request.setAttribute("bookOrder", bookOrder);
		
		List<OrderStatus> orderStatuses = orderDAO.getAllOrderStatuses();
		request.setAttribute("orderStatuses", orderStatuses);
		
		return "updateBookOrder.jsp";
	}

}
