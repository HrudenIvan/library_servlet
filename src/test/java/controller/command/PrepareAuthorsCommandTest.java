package controller.command;

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

public class PrepareAuthorsCommandTest {
	
	@Test
	public void ExecuteShouldForwardToUpdateAuthorPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("aId")).thenReturn("1");
		when(req.getRequestDispatcher("updateAuthor.jsp")).thenReturn(reqDisp);
		try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
			authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
			Command addAuthor = CommandFactory.getCommand("prepareAuthor");
			addAuthor.execute(req, resp);
			
			verify(reqDisp).forward(req, resp);
		}
	}

}
