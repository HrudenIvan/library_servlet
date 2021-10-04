package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

/**
 * Command to access librarian page
 */
public class PrepareLibrarianCommand implements Command {

	/**
	 * Stores current {@link User}, list of new {@link BookOrder}s, list of users with open BookOrders in request and forwards to librarian page
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
		List<BookOrder> bookOrders = orderDAO.getNewBookOrders();
		request.setAttribute("bookOrders", bookOrders);

		UserDAO userDAO = UserDAOImpl.getInstance();
		List<User> users = userDAO.getAllUsersWithOpenOrders();
		request.setAttribute("users", users);

		HttpSession session = request.getSession();
		Long currentUserId = (Long) session.getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(currentUserId);
		request.setAttribute("currentUser", currentUser);

		request.getRequestDispatcher("librarian.jsp").forward(request, response);
	}

}
