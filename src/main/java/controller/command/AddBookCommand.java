package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.AuthorDAO;
import model.DAO.AuthorDAOImpl;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;

import util.Validator;

public class AddBookCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Book book = new Book();
		book.setTitle(request.getParameter("title"));
		String total = request.getParameter("total");
		if (total.isBlank()) {
			book.setQuantity(0);
		} else {
			book.setQuantity(Integer.parseInt(total));
		}
		book.setAvailable(book.getQuantity());
		Author author = new Author();
		author.setId(Long.valueOf(request.getParameter("aId")));
		book.setAuthor(author);
		Publisher publisher = new Publisher();
		publisher.setId(Long.valueOf(request.getParameter("pId")));
		book.setPublisher(publisher);
		String releaseDate = request.getParameter("releaseDate");
		if (releaseDate.isBlank()) {
			book.setReleaseDate(0);
		} else {
			book.setReleaseDate(Integer.parseInt(releaseDate));
		}
		
		PublisherDAO publisherDAO = PublisherDAOImpl.getInstance();
		AuthorDAO authorDAO = AuthorDAOImpl.getInstance();
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.valideteBook(book, errors)) {
			request.setAttribute("aId", book.getAuthor().getId());
			request.setAttribute("pId", book.getPublisher().getId());
			request.setAttribute("authors", authorDAO.getAllAuthors());
			request.setAttribute("publishers", publisherDAO.getAllPublishers());
			request.setAttribute("book", book);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("addBook.jsp").forward(request, response);
			return;
		}
		
		BookDAO bookDAO = BookDAOImpl.getInstance();
		bookDAO.addBook(book);
		
		response.sendRedirect("main?action=getAllBooks");
	}

}
