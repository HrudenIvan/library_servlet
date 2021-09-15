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

import dao.OrderDAOMock;
import dao.UserDAOMock;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAOImpl;

public class SubscriptionCommandTest {

	@Test
	public void ExecuteShouldForwardSubscriptionPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("uId")).thenReturn("1");
		when(req.getRequestDispatcher("subscription.jsp")).thenReturn(reqDisp);
		try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
			orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
			try (MockedStatic<UserDAOImpl> userDAOMock = Mockito.mockStatic(UserDAOImpl.class)) {
				userDAOMock.when(() -> UserDAOImpl.getInstance()).thenReturn(new UserDAOMock());
				Command com = CommandFactory.getCommand("subscription");
				com.execute(req, resp);

				verify(reqDisp).forward(req, resp);
			}
		}
	}
}
