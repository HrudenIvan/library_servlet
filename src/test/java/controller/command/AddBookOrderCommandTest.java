package controller.command;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dao.OrderDAOMock;
import model.DAO.OrderDAOImpl;

public class AddBookOrderCommandTest {

	@Test
	public void ExecuteShouldRedirect() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("bId")).thenReturn("3");
		when(req.getParameter("uId")).thenReturn("3");
		when(req.getParameter("otId")).thenReturn("1");
		try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
			orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
			Command com = CommandFactory.getCommand("addBookOrder");
			com.execute(req, resp);

			verify(resp).sendRedirect("main?action=getAllBooks");
		}
	}
}
