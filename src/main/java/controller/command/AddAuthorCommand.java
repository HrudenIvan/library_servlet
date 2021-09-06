package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

public class AddAuthorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Author author = new Author();
		author.setFirstName(request.getParameter("firstName"));
		author.setLastName(request.getParameter("lastName"));
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		authorDAO.addAuthor(author);
		return "main?action=getAllAuthors";
	}

}
