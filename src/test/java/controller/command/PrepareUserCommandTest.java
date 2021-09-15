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

import dao.UserDAOMock;
import model.DAO.UserDAOImpl;

public class PrepareUserCommandTest {

	@Test
	public void ExecuteShouldForwardToUpdateUserPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("uId")).thenReturn("1");
		when(req.getRequestDispatcher("updateUser.jsp")).thenReturn(reqDisp);
		try (MockedStatic<UserDAOImpl> userDAOMock = Mockito.mockStatic(UserDAOImpl.class)) {
			userDAOMock.when(() -> UserDAOImpl.getInstance()).thenReturn(new UserDAOMock());
			Command com = CommandFactory.getCommand("prepareUser");
			com.execute(req, resp);

			verify(reqDisp).forward(req, resp);
		}
	}

}
