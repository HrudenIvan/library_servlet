package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;
import util.Validator;

public class UpdateAuthorCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		author.setFirstName(request.getParameter("firstName"));
		author.setLastName(request.getParameter("lastName"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateAuthor(author, errors)) {
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
