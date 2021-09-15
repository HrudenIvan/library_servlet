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
import model.DAO.BookDAOImpl;

public class GetAllBooksCommandTest {
	
	@Test
	public void ExecuteShouldForwardToBooksPage() throws Exception {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		RequestDispatcher reqDisp = mock(RequestDispatcher.class);
		when(req.getRequestDispatcher("books.jsp")).thenReturn(reqDisp);
		try (MockedStatic<BookDAOImpl> bookDAOMock = Mockito.mockStatic(BookDAOImpl.class)) {
			bookDAOMock.when(() -> BookDAOImpl.getInstance()).thenReturn(new BookDAOMock());
			Command com = CommandFactory.getCommand("getAllBooks");
			com.execute(req, resp);
			
			verify(reqDisp).forward(req, resp);
		}
	}

}
