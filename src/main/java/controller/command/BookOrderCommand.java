package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;

public class BookOrderCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Long bookId = Long.valueOf(request.getParameter("bId"));
		Long userId = Long.valueOf(request.getParameter("uId"));
		int orderTypeId = Integer.parseInt(request.getParameter("otId"));
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		orderDAO.addOrder(userId, bookId, orderTypeId);
		return "main?action=getAllBooks";
	}

}
