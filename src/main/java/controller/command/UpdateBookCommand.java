package controller.command;

import javax.servlet.http.HttpServletRequest;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;

public class UpdateBookCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Book book = new Book();
		book.setId(Long.valueOf(request.getParameter("bId")));
		book.setTitle(request.getParameter("title"));
		book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		book.setAvailable(Integer.parseInt(request.getParameter("available")));
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		book.setAuthor(author);
		Publisher publisher = new Publisher();
		publisher.setId(Long.valueOf(request.getParameter("pId")));
		book.setPublisher(publisher);
		book.setReleaseDate(Integer.parseInt(request.getParameter("releaseDate")));
		BookDAO bookDAO = BookDAOImpl.getInstance();
		bookDAO.updateBook(book);
		return "main?action=getAllBooks";
	}

}
