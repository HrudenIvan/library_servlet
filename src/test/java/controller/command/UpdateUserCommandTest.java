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

import dao.UserDAOMock;
import model.DAO.UserDAOImpl;
import util.Localizer;

public class UpdateUserCommandTest {

	@Test
	public void whenInvalidUserThenExecuteShouldForwardToUpdateUserPage() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getParameter("uId")).thenReturn("1");
			when(req.getParameter("login")).thenReturn("");
			when(req.getParameter("firstName")).thenReturn("");
			when(req.getParameter("lastName")).thenReturn("");
			when(req.getParameter("penalty")).thenReturn("");
			when(req.getParameter("tId")).thenReturn("1");
			when(req.getParameter("password")).thenReturn("");
			when(req.getParameter("password1")).thenReturn("");
			when(req.getRequestDispatcher("updateUser.jsp")).thenReturn(reqDisp);
			try (MockedStatic<UserDAOImpl> userDAOMock = Mockito.mockStatic(UserDAOImpl.class)) {
				userDAOMock.when(() -> UserDAOImpl.getInstance()).thenReturn(new UserDAOMock());
				Command com = CommandFactory.getCommand("updateUser");
				com.execute(req, resp);

				verify(reqDisp).forward(req, resp);
			}
		}
	}

	@Test
	public void whenValidUserThenExecuteShouldRedirectToAllUserPage() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			try (MockedStatic<UserDAOImpl> userDAOMock = Mockito.mockStatic(UserDAOImpl.class)) {
				userDAOMock.when(() -> UserDAOImpl.getInstance()).thenReturn(new UserDAOMock());
				HttpServletRequest req = mock(HttpServletRequest.class);
				HttpServletResponse resp = mock(HttpServletResponse.class);
				when(req.getParameter("uId")).thenReturn("1");
				when(req.getParameter("login")).thenReturn("1");
				when(req.getParameter("firstName")).thenReturn("1");
				when(req.getParameter("lastName")).thenReturn("1");
				when(req.getParameter("penalty")).thenReturn("0");
				when(req.getParameter("tId")).thenReturn("1");
				when(req.getParameter("password")).thenReturn("1234567890");
				when(req.getParameter("password1")).thenReturn("1234567890");
				Command com = CommandFactory.getCommand("updateUser");
				com.execute(req, resp);
				
				verify(resp).sendRedirect("main?action=getAllUsers");
			}
		}
	}

}
