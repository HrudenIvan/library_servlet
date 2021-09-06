package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class SubscriptionCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long userId = Long.valueOf(request.getParameter("uId"));
		User user=userDAO.getUserById(userId);
		request.setAttribute("user", user);
		
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getUserOpenBookOrders(userId);
		request.setAttribute("bookOrders", bookOrders);
		
		return "subscription.jsp";
	}

}
