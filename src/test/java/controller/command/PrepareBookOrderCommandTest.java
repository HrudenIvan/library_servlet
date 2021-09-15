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

import dao.BookDAOMock;
import dao.OrderDAOMock;
import model.DAO.BookDAOImpl;
import model.DAO.OrderDAOImpl;

public class PrepareBookOrderCommandTest {

	@Test
	public void ExecuteShouldForwardToBookOrderPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("bId")).thenReturn("1");
		when(req.getRequestDispatcher("orderBook.jsp")).thenReturn(reqDisp);
		try (MockedStatic<BookDAOImpl> bookDAOMock = Mockito.mockStatic(BookDAOImpl.class)) {
			bookDAOMock.when(() -> BookDAOImpl.getInstance()).thenReturn(new BookDAOMock());
			try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
				orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
				Command addAuthor = CommandFactory.getCommand("prepareBookOrder");
				addAuthor.execute(req, resp);

				verify(reqDisp).forward(req, resp);
			}
		}
	}

}
