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

public class AddAuthorCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Author author = new Author();
		author.setFirstName(request.getParameter("firstName"));
		author.setLastName(request.getParameter("lastName"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validateAuthor(request, author, errors)) {
			request.setAttribute("author", author);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("addAuthor.jsp").forward(request, response);
			return;
		}
		
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		authorDAO.addAuthor(author);
		
		response.sendRedirect("main?action=getAllAuthors");
	}

}
