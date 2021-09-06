package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class PrepareCabinetCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long userId = (Long) request.getSession().getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(userId);
		request.setAttribute("currentUser", currentUser);
		
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getUserOrders(userId);
		request.setAttribute("bookOrders", bookOrders);
		
		return "privateCabinet.jsp";
	}

}
