package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.BookDAO;
import model.DAO.BookDAOImpl;
import model.entity.Book;

import util.BookComporators;
import util.BookComporators.OrderBy;
import util.BookComporators.SortBy;
import util.Util;

public class GetAllBooksCommand implements Command {
	private static final int BOOKS_PER_PAGE = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		String sort = Util.defineSortAndStoreInCookie(request, response);
		request.setAttribute("sort", sort);

		String order = Util.defineOrderAndStoreInCookie(request, response);
		request.setAttribute("order", order);

		String title = request.getParameter("title");
		if (title == null) {
			title = "";
		}
		request.setAttribute("title", title);

		String aLastname = request.getParameter("aLastname");
		if (aLastname == null) {
			aLastname = "";
		}
		request.setAttribute("aLastname", aLastname);

		String aFirstname = request.getParameter("aFirstname");
		if (aFirstname == null) {
			aFirstname = "";
		}
		request.setAttribute("aFirstname", aFirstname);

		List<SortBy> sortBy = BookComporators.getAllSortBy(request);
		request.setAttribute("sortBy", sortBy);

		List<OrderBy> orderBy = BookComporators.getAllOrderBy(request);
		request.setAttribute("orderBy", orderBy);

		int page = 0;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		BookDAO bookDAO = BookDAOImpl.getInstance();
		int booksCount = bookDAO.booksCount(sort, order, title, aLastname, aFirstname);
		int pagesCount = (int) Math.ceil( booksCount / (double) BOOKS_PER_PAGE);
		String paginationNav = Util.buildPaginationNav(page, pagesCount, title, aLastname, aFirstname);
		request.setAttribute("paginationNav", paginationNav);
		
		List<Book> books = bookDAO.getAllBooks(page, BOOKS_PER_PAGE, sort, order, title, aLastname, aFirstname);
		request.setAttribute("books", books);

		request.getRequestDispatcher("books.jsp").forward(request, response);
	}

}
