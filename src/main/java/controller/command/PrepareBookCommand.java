package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;

public class PrepareBookCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		BookDAO bookDAO = BookDAOImpl.getInstance();
		long id = Long.valueOf(request.getParameter("bId"));
		Book book = bookDAO.getBook(id);
		request.setAttribute("book", book);

		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		List<Publisher> publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);

		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		List<Author> authors = authorDAO.getAllAuthors();
		request.setAttribute("authors", authors);

		return "updateBook.jsp";
	}

}
