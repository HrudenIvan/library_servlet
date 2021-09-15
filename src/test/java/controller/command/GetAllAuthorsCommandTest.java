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

public class GetAllAuthorsCommandTest {

	@Test
	public void ExecuteShouldForwardToAuthorsPage() throws Exception {
		try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
			authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getRequestDispatcher("authors.jsp")).thenReturn(reqDisp);
			Command com = CommandFactory.getCommand("getAllAuthors");
			com.execute(req, resp);
			
			verify(reqDisp).forward(req, resp);
		}
	}

}
