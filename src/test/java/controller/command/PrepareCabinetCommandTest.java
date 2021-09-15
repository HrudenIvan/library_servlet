package controller.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dao.OrderDAOMock;
import dao.UserDAOMock;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAOImpl;

public class PrepareCabinetCommandTest {

	@Test
	public void executeShouldForvardToPrivateCabinetPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		HttpSession session = mock(HttpSession.class);
		when(req.getSession()).thenReturn(session);
		when(session.getAttribute("currentUserId")).thenReturn(1L);
		when(req.getRequestDispatcher("privateCabinet.jsp")).thenReturn(reqDisp);
		try (MockedStatic<UserDAOImpl> userDAOMock = Mockito.mockStatic(UserDAOImpl.class)) {
			userDAOMock.when(() -> UserDAOImpl.getInstance()).thenReturn(new UserDAOMock());
			try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
				orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
				Command com = CommandFactory.getCommand("prepareCabinet");
				com.execute(req, resp);

				verify(reqDisp).forward(req, resp);
			}
		}
	}

}
