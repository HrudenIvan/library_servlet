package controller.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

public class AddAuthorLinkCommandTest {
	
	@Test
	public void whenExecuteThenMustForvard() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getRequestDispatcher("addAuthor.jsp")).thenReturn(reqDisp);
		Command com = CommandFactory.getCommand("addAuthorLink");
		com.execute(req, resp);
		
		verify(reqDisp).forward(req, resp);
	}

}
