package controller.command;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Author;
import model.entity.Publisher;
import model.entity.Book;

/**
 * Command to access {@link Book} adding page
 */
public class AddBookLinkCommand implements Command {

	/**
	 * Adds lists of {@link Publisher} and {@link Author} to request. Forwards to {@link Book} adding page
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, DBException {
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		List<Publisher> publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);

		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.setAttribute("authors", authors);
		
		request.getRequestDispatcher("addBook.jsp").forward(request, response);
	}

}
