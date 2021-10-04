package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

/**
 * Command to access {@link Publisher} update page
 */
public class PreparePublisherCommand implements Command {

	/**
	 * Stores needed {@link Publisher} in request and forwards to update publisher page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		Long id = Long.valueOf(request.getParameter("pId"));
		Publisher publisher = pubDAO.getPublisher(id);
		request.setAttribute("publisher", publisher);
		
		request.getRequestDispatcher("updatePublisher.jsp").forward(request, response);
	}

}
