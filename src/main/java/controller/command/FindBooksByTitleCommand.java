package controller.command;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Book;
import util.BookComporators;

public class FindBooksByTitleCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sortOrder = request.getParameter("sortOrder");
		request.setAttribute("sortOrderPrev", sortOrder);
		Comparator<Book> comparator = BookComporators.defineComparator(sortOrder);
		
		BookDAO bookDAO = BookDAOImpl.getInstance();
		String title = request.getParameter("title");
		List<Book> books = bookDAO.findBooksByTitle(title);
		books.sort(comparator);
		request.setAttribute("title", title);
		request.setAttribute("books", books);
		
		List<BookComporators.Comparators> comparators = BookComporators.getAll();
		request.setAttribute("comparators", comparators);

		request.getRequestDispatcher("books.jsp").forward(request, response);
	}

}
