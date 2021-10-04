package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

/**
 * Command to access all {@link Publisher}s page
 */
public class GetAllPublishersCommand implements Command {

	/**
	 * Adds list of {@link Publisher}s to request and forwards to all publishers page
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
		List<Publisher> publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);

		request.getRequestDispatcher("publishers.jsp").forward(request, response);
	}

}
