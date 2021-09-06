package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

public class UpdateAuthorCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		author.setFirstName(request.getParameter("firstName"));
		author.setLastName(request.getParameter("lastName"));
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		authorDAO.updateAuthor(author);
		return "main?action=getAllAuthors";
	}

}
