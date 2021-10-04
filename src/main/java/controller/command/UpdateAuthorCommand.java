package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;
import util.Validator;

/**
 * Command for updating {@link Author} 
 */
public class UpdateAuthorCommand implements Command {

	/**
	 * Method for handling {@link Author} updating. If updated author not valid, then forwards to update author page with error messages,
	 * otherwise redirects to all authors page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		author.setFirstName(request.getParameter("firstName"));
		author.setLastName(request.getParameter("lastName"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateAuthor(request, author, errors)) {
			request.setAttribute("author", author);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("updateAuthor.jsp").forward(request, response);
			return;
		}
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		authorDAO.updateAuthor(author);
		
		response.sendRedirect("main?action=getAllAuthors");
	}

}
