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
import model.DAO.AuthorDAOImpl;
import util.Localizer;

public class AddAuthorCommandTest {

	@Test
	public void whenNotValidAuthorThenExecuteMustForvard() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getParameter("firstName")).thenReturn("");
			when(req.getParameter("lastName")).thenReturn("");
			when(req.getRequestDispatcher("addAuthor.jsp")).thenReturn(reqDisp);
			Command addAuthor = CommandFactory.getCommand("addAuthor");
			addAuthor.execute(req, resp);
			
			verify(reqDisp).forward(req, resp);
		}
	}

	@Test
	public void whenValidAuthorThenExecuteMustRedirect() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("firstName")).thenReturn("123");
		when(req.getParameter("lastName")).thenReturn("123");
		try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
			authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
			Command addAuthor = CommandFactory.getCommand("addAuthor");
			addAuthor.execute(req, resp);
			
			verify(resp).sendRedirect("main?action=getAllAuthors");
		}
	}

}
