package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

public class PrepareAuthorsCommand implements Command {

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
