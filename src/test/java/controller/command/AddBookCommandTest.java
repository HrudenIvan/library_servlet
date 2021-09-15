package controller.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dao.AuthorDAOMock;
import dao.BookDAOMock;
import dao.PublisherDAOMock;
import model.DAO.AuthorDAOImpl;
import model.DAO.BookDAOImpl;
import model.DAO.PublisherDAOImpl;
import util.Localizer;

public class AddBookCommandTest {

	@Test
	public void whenNotValidBookThenExecuteMustForvard() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getParameter("title")).thenReturn("");
			when(req.getParameter("total")).thenReturn("");
			when(req.getParameter("aId")).thenReturn("1");
			when(req.getParameter("pId")).thenReturn("1");
			when(req.getParameter("releaseDate")).thenReturn("");
			when(req.getRequestDispatcher("addBook.jsp")).thenReturn(reqDisp);
			try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
				authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
				try (MockedStatic<PublisherDAOImpl> publisherDAOMock = Mockito.mockStatic(PublisherDAOImpl.class)) {
					publisherDAOMock.when(() -> PublisherDAOImpl.getInstance()).thenReturn(new PublisherDAOMock());
					Command com = CommandFactory.getCommand("addBook");
					com.execute(req, resp);

					verify(reqDisp).forward(req, resp);
				}
			}
		}
	}

	@Test
	public void whenValidBookThenExecuteMustRedirect() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("title")).thenReturn("12");
		when(req.getParameter("total")).thenReturn("1");
		when(req.getParameter("aId")).thenReturn("1");
		when(req.getParameter("pId")).thenReturn("1");
		when(req.getParameter("releaseDate")).thenReturn("2021");
		try (MockedStatic<BookDAOImpl> bookDAOMock = Mockito.mockStatic(BookDAOImpl.class)) {
			bookDAOMock.when(() -> BookDAOImpl.getInstance()).thenReturn(new BookDAOMock());
			Command com = CommandFactory.getCommand("addBook");
			com.execute(req, resp);
			
			verify(resp).sendRedirect("main?action=getAllBooks");
		}
	}

}
