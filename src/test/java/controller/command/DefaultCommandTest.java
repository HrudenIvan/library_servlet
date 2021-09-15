package controller.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;

public class DefaultCommandTest {
	
	@Test
	public void whenAdminThenExecuteShouldForwardToPrepareAdmin () throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("currentUserType")).thenReturn("admin");
		when(req.getRequestDispatcher("main?action=prepareAdmin")).thenReturn(reqDisp);
		Command com = CommandFactory.getCommand("default");
		com.execute(req, resp);
		
		verify(reqDisp).forward(req, resp);
	}
	
	@Test
	public void whenLibrarianThenExecuteShouldForwardToPrepareLibrarian () throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("currentUserType")).thenReturn("librarian");
		when(req.getRequestDispatcher("main?action=prepareLibrarian")).thenReturn(reqDisp);
		Command com = CommandFactory.getCommand("default");
		com.execute(req, resp);
		
		verify(reqDisp).forward(req, resp);
	}
	
	@Test
	public void whenNotAdminOrLibrarianThenExecuteShouldForwardToGetAllBooks () throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("currentUserType")).thenReturn("");
		when(req.getRequestDispatcher("main?action=getAllBooks")).thenReturn(reqDisp);
		Command com = CommandFactory.getCommand("default");
		com.execute(req, resp);
		
		verify(reqDisp).forward(req, resp);
	}

}
