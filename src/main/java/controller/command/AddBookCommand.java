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
 * Command for adding {@link Book}
 */
public class AddBookCommand implements Command {

	/**
	 * Method for handling {@link Book} adding. If new book not valid, then forwards to add book page with error messages,
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
		book.setTitle(request.getParameter("title").trim());
		String total = request.getParameter("total").trim();
		if (total.isEmpty()) {
			book.setQuantity(0);
		} else {
			book.setQuantity(Integer.parseInt(total));
		}
		book.setAvailable(book.getQuantity());
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
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.valideteBook(request, book, errors)) {
			request.setAttribute("aId", book.getAuthor().getId());
			request.setAttribute("pId", book.getPublisher().getId());
			request.setAttribute("authors", authorDAO.getAllAuthors());
			request.setAttribute("publishers", publisherDAO.getAllPublishers());
			request.setAttribute("book", book);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}
		
		BookDAO bookDAO = BookDAOImpl.getInstance();
		bookDAO.addBook(book);
		
		response.sendRedirect("main?action=getAllBooks");
	}

}
