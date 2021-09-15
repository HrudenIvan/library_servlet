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

import dao.PublisherDAOMock;
import model.DAO.PublisherDAOImpl;

public class GetAllPublishersCommandTest {

	@Test
	public void ExecuteShouldForwardToPublishersPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getRequestDispatcher("publishers.jsp")).thenReturn(reqDisp);
		try (MockedStatic<PublisherDAOImpl> publisherDAOMock = Mockito.mockStatic(PublisherDAOImpl.class)) {
			publisherDAOMock.when(() -> PublisherDAOImpl.getInstance()).thenReturn(new PublisherDAOMock());
			Command com = CommandFactory.getCommand("getAllPublishers");
			com.execute(req, resp);

			verify(reqDisp).forward(req, resp);
		}
	}

}
