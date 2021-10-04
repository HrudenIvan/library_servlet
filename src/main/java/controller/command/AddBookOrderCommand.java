package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.BookOrder;

/**
 * Command for adding {@link BookOrder}
 */
public class AddBookOrderCommand implements Command {

	/**
	 * Method for handling {@link BookOrder} adding. Redirects to all books page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Long bookId = Long.valueOf(request.getParameter("bId"));
		Long userId = Long.valueOf(request.getParameter("uId"));
		int orderTypeId = Integer.parseInt(request.getParameter("otId"));
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		orderDAO.addOrder(userId, bookId, orderTypeId);
		
		response.sendRedirect("main?action=getAllBooks");
	}

}
