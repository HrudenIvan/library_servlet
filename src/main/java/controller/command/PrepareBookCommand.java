package controller.command;

import java.io.IOException;
import java.util.List;

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

/**
 * Command to access {@link Book} update page
 */
public class PrepareBookCommand implements Command {

	/**
	 * Stores needed {@link Book} in request and forwards to update book page
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
		long id = Long.valueOf(request.getParameter("bId"));
		Book book = bookDAO.getBook(id);
		request.setAttribute("book", book);

		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		List<Publisher> publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);

		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.setAttribute("authors", authors);

		request.getRequestDispatcher("updateBook.jsp").forward(request, response);
	}

}
