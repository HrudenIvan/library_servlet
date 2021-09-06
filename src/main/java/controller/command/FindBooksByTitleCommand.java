package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Book;

public class FindBooksByTitleCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		BookDAO bookDAO = BookDAOImpl.getInstance();
		String title = request.getParameter("title");
		List<Book> books = bookDAO.findBooksByTitle(title);
		request.setAttribute("books", books);
		
		return "books.jsp";
	}

}
