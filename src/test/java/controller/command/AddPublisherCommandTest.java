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

import dao.PublisherDAOMock;
import model.DAO.PublisherDAOImpl;
import util.Localizer;

public class AddPublisherCommandTest {

	@Test
	public void whenNotValidPublisherThenExecuteShouldForward() throws Exception {
		try (MockedStatic<Localizer> util = Mockito.mockStatic(Localizer.class)) {
			util.when(() -> Localizer.getString(any(), any())).thenReturn("error");
			HttpServletRequest req = mock(HttpServletRequest.class);
			HttpServletResponse resp = mock(HttpServletResponse.class);
			RequestDispatcher reqDisp = mock(RequestDispatcher.class);
			when(req.getParameter("name")).thenReturn("");
			when(req.getRequestDispatcher("addPublisher.jsp")).thenReturn(reqDisp);
			Command com = CommandFactory.getCommand("addPublisher");
			com.execute(req, resp);

			verify(reqDisp).forward(req, resp);
		}
	}

	@Test
	public void whenValidPublisherThenExecuteShouldRedirect() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		when(req.getParameter("name")).thenReturn("3");
		try (MockedStatic<PublisherDAOImpl> publisherDAOMock = Mockito.mockStatic(PublisherDAOImpl.class)) {
			publisherDAOMock.when(() -> PublisherDAOImpl.getInstance()).thenReturn(new PublisherDAOMock());
			Command com = CommandFactory.getCommand("addPublisher");
			com.execute(req, resp);

			verify(resp).sendRedirect("main?action=getAllPublishers");
		}
	}
}
