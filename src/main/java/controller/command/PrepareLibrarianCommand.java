package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class PrepareLibrarianCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getNewBookOrders();
		request.setAttribute("bookOrders", bookOrders);
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsersWithOpenOrders(); 
		request.setAttribute("users", users);
		
		Long currentUserId = (Long) request.getSession().getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(currentUserId);
		request.setAttribute("currentUser", currentUser);
		
		return "librarian.jsp";
	}

}
