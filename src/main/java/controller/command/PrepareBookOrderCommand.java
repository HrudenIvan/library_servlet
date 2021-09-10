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

public class PrepareBookOrderCommand implements Command {

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
