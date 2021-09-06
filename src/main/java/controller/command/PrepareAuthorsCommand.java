package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.entity.Author;

public class PrepareAuthorsCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Long id = Long.valueOf(request.getParameter("aId"));
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		Author author = new Author();
		author = authorDAO.getAuthor(id);
		request.setAttribute("author", author);
		return "updateAuthor.jsp";
	}

}
