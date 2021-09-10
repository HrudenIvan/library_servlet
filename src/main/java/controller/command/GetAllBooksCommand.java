package controller.command;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Book;

import util.BookComporators;

public class GetAllBooksCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		String sortOrder = request.getParameter("sortOrder");
		request.setAttribute("sortOrder", sortOrder);
		Comparator<Book> comparator = BookComporators.defineComparator(sortOrder);

		BookDAO bookDAO = BookDAOImpl.getInstance();
		List<Book> books = bookDAO.getAllBooks();
		books.sort(comparator);
		request.setAttribute("books", books);

		List<BookComporators.Comparators> comparators = BookComporators.getAll();
		request.setAttribute("comparators", comparators);

		request.getRequestDispatcher("books.jsp").forward(request, response);
	}

}
