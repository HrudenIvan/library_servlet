package controller.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Author;
import model.entity.Publisher;

public class AddBookLinkCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		List<Publisher> publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);

		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.setAttribute("authors", authors);
		
		return "addBook.jsp";
	}

}
