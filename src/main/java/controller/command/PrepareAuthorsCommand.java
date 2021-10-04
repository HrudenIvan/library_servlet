package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

/**
 * Command to access {@link Author} update page
 */
public class PrepareAuthorsCommand implements Command {

	/**
	 * Stores needed {@link Author} in request and forwards to update author page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Long id = Long.valueOf(request.getParameter("aId"));
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		Author author = new Author();
		author = authorDAO.getAuthor(id);
		request.setAttribute("author", author);
		
		request.getRequestDispatcher("updateAuthor.jsp").forward(request, response);
	}

}
