package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.entity.Book;
import model.entity.OrderType;

/**
 * Command to access order book page
 */
public class PrepareBookOrderCommand implements Command {

	/**
	 * Stores {@link Book}, list of {@link OrderType}s in request and forwards to order book page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		BookDAO bookDAO = BookDAOImpl.getInstance();
		Long bookId = Long.valueOf(request.getParameter("bId"));
		Book book = bookDAO.getBook(bookId);
		request.setAttribute("book", book);

		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<OrderType> orderTypes = orderDAO.getAllOrderTypes();
		request.setAttribute("orderTypes", orderTypes);

		request.getRequestDispatcher("orderBook.jsp").forward(request, response);
	}

}
