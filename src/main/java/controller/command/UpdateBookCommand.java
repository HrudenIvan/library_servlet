package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;
import util.Validator;

/**
 * Command for updating {@link Book}
 */
public class UpdateBookCommand implements Command {

	/**
	 * Method for handling {@link Book} updating. If updated book not valid, then forwards to update book page with error messages,
	 * otherwise redirects to all books page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Book book = new Book();
		book.setId(Long.valueOf(request.getParameter("bId")));
		book.setTitle(request.getParameter("title").trim());
		String quantity = request.getParameter("quantity").trim();
		if (quantity.isEmpty()) {
			book.setQuantity(0);
		} else {
			book.setQuantity(Integer.parseInt(quantity));
		}
		book.setAvailable(Integer.parseInt(request.getParameter("available")));
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		book.setAuthor(author);
		Publisher publisher = new Publisher();
		publisher.setId(Long.valueOf(request.getParameter("pId")));
		book.setPublisher(publisher);
		String releaseDate = request.getParameter("releaseDate");
		if (releaseDate.isEmpty()) {
			book.setReleaseDate(0);
		} else {
			book.setReleaseDate(Integer.parseInt(releaseDate));
		}
		
		PublisherDAO publisherDAO = PublisherDAOImpl.getInstance();
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		int oldQuantity = Integer.parseInt(request.getParameter("oldQuantity"));
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.valideteAndUpdateBookUpdate(request, book, oldQuantity, errors)) {
			request.setAttribute("authors", authorDAO.getAllAuthors());
			request.setAttribute("publishers", publisherDAO.getAllPublishers());
			request.setAttribute("book", book);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("updateBook.jsp").forward(request, response);
			return;
		}
		
		BookDAO bookDAO = BookDAOImpl.getInstance();
		bookDAO.updateBook(book);
		
		response.sendRedirect("main?action=getAllBooks");
	}

}
