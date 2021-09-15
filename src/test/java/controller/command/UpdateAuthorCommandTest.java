package controller.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dao.AuthorDAOMock;
import model.DAO.AuthorDAOImpl;
import util.Localizer;

public class UpdateAuthorCommandTest {
	
	@Test
	public void whenInValidAuthorThenExecuteShouldRedirectToUpdateUathorpage() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getParameter("aId")).thenReturn("1");
			when(req.getParameter("firstName")).thenReturn("");
			when(req.getParameter("lastName")).thenReturn("");
			when(req.getRequestDispatcher("updateAuthor.jsp")).thenReturn(reqDisp);
			Command addAuthor = CommandFactory.getCommand("updateAuthor");
			addAuthor.execute(req, resp);
			
			verify(reqDisp).forward(req, resp);
		}
	}
	
	@Test
	public void whenValidAuthorThenExecuteShouldRedirectToAuthorsPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("aId")).thenReturn("3");
		when(req.getParameter("firstName")).thenReturn("123");
		when(req.getParameter("lastName")).thenReturn("123");
		try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
			authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
			Command addAuthor = CommandFactory.getCommand("updateAuthor");
			addAuthor.execute(req, resp);
			
			verify(resp).sendRedirect("main?action=getAllAuthors");
		}
	}

}
