package controller.command;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

/**
 * Command to access all {@link Author}s page
 */
public class GetAllAuthorsCommand implements Command {

	/**
	 * Adds list of {@link Author}s to request and forwards to all authors page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.setAttribute("authors", authors);

		request.getRequestDispatcher("authors.jsp").forward(request, response);
	}

}
