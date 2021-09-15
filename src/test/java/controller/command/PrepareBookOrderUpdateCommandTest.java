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
import model.DAO.OrderDAOImpl;

public class PrepareBookOrderUpdateCommandTest {
	
	@Test
	public void ExecuteShouldForwardToUpdateBookOrderPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("uId")).thenReturn("1");
		when(req.getParameter("bId")).thenReturn("1");
		when(req.getRequestDispatcher("updateBookOrder.jsp")).thenReturn(reqDisp);
		try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
			orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
			Command addAuthor = CommandFactory.getCommand("prepareBookOrderUpdate");
			addAuthor.execute(req, resp);

			verify(reqDisp).forward(req, resp);
		}
	}

}
