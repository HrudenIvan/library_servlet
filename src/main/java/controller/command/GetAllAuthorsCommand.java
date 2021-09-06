package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

public class GetAllAuthorsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.getSession().setAttribute("authors", authors);
		return "authors.jsp";
	}

}
