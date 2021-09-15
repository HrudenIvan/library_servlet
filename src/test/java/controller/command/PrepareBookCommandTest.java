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

import dao.AuthorDAOMock;
import dao.BookDAOMock;
import dao.PublisherDAOMock;
import model.DAO.AuthorDAOImpl;
import model.DAO.BookDAOImpl;
import model.DAO.PublisherDAOImpl;

public class PrepareBookCommandTest {

	@Test
	public void ExecuteShouldForwardToUpdateBookPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getParameter("bId")).thenReturn("1");
		when(req.getRequestDispatcher("updateBook.jsp")).thenReturn(reqDisp);
		try (MockedStatic<AuthorDAOImpl> authorDAOMock = Mockito.mockStatic(AuthorDAOImpl.class)) {
			authorDAOMock.when(() -> AuthorDAOImpl.getInstance()).thenReturn(new AuthorDAOMock());
			try (MockedStatic<PublisherDAOImpl> publisherDAOMock = Mockito.mockStatic(PublisherDAOImpl.class)) {
				publisherDAOMock.when(() -> PublisherDAOImpl.getInstance()).thenReturn(new PublisherDAOMock());
				try (MockedStatic<BookDAOImpl> bookDAOMock = Mockito.mockStatic(BookDAOImpl.class)) {
					bookDAOMock.when(() -> BookDAOImpl.getInstance()).thenReturn(new BookDAOMock());
					Command addAuthor = CommandFactory.getCommand("prepareBook");
					addAuthor.execute(req, resp);

					verify(reqDisp).forward(req, resp);
				}
			}
		}
	}

}
