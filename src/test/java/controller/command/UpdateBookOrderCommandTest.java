package controller.command;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import dao.OrderDAOMock;
import model.DAO.OrderDAOImpl;
import util.Localizer;

public class UpdateBookOrderCommandTest {
	
	@Test
	public void whenInValidBookOrderThenExecuteShouldForwardToUpdateBookOrderPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("bId")).thenReturn("1");
		when(req.getParameter("uId")).thenReturn("1");
		when(req.getParameter("orderType")).thenReturn("1");
		when(req.getParameter("title")).thenReturn(" ");
		when(req.getParameter("orderDate")).thenReturn(LocalDateTime.now().toString());
		when(req.getParameter("penalty")).thenReturn("0");
		when(req.getParameter("openDate")).thenReturn(LocalDate.now().toString());
		when(req.getParameter("closeDate")).thenReturn(LocalDate.now().toString());
		when(req.getParameter("osId")).thenReturn("1");
		when(req.getParameter("oldOsId")).thenReturn("2");
		when(req.getParameter("oldCloseDate")).thenReturn(LocalDate.now().toString());
		when(req.getRequestDispatcher("updateBookOrder.jsp")).thenReturn(reqDisp);
		try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
			orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
			try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
				util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
				Command com = CommandFactory.getCommand("updateBookOrder");
				com.execute(req, resp);

				verify(reqDisp).forward(req, resp);
			}
		}
	}
	
	@Test
	public void whenValidBookOrderThenExecuteShouldRedirectToPrepareLibrarian() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("bId")).thenReturn("1");
		when(req.getParameter("uId")).thenReturn("1");
		when(req.getParameter("orderType")).thenReturn("1");
		when(req.getParameter("title")).thenReturn("123");
		when(req.getParameter("orderDate")).thenReturn(LocalDateTime.now().toString());
		when(req.getParameter("penalty")).thenReturn("0");
		when(req.getParameter("openDate")).thenReturn(LocalDate.now().toString());
		when(req.getParameter("closeDate")).thenReturn(LocalDate.now().toString());
		when(req.getParameter("osId")).thenReturn("1");
		when(req.getParameter("oldOsId")).thenReturn("1");
		when(req.getParameter("oldCloseDate")).thenReturn(LocalDate.now().toString());
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			try (MockedStatic<OrderDAOImpl> orderDAOMock = Mockito.mockStatic(OrderDAOImpl.class)) {
				orderDAOMock.when(() -> OrderDAOImpl.getInstance()).thenReturn(new OrderDAOMock());
				Command com = CommandFactory.getCommand("updateBookOrder");
				com.execute(req, resp);
				
				verify(resp).sendRedirect("main?action=prepareLibrarian");
			}
		}
	}

}
