package controller.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Book;
import util.BookComporators;

public class GetAllBooksCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		String sortOrder = request.getParameter("sortOrder");
		Comparator<Book> comparator = BookComporators.defineComparator(sortOrder);

		BookDAO bookDAO = BookDAOImpl.getInstance();
		List<Book> books = bookDAO.getAllBooks();
		books.sort(comparator);
		request.setAttribute("books", books);
		
		List<BookComporators.Comparators> comparators = new ArrayList<>();
		for(BookComporators.Comparators comp : BookComporators.Comparators.values()) {
			comparators.add(comp);
		}
		request.setAttribute("comparators", comparators);
				
		return "books.jsp";
	}

}
