package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;
import util.Validator;

/**
 * Command for adding {@link Publisher}
 */
public class AddPublisherCommand implements Command {

	/**
	 * Method for handling {@link Publisher} adding. If new publisher not valid, then forwards to add publisher page with error messages,
	 * otherwise redirects to all publishers page 
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, DBException {
		Publisher publisher = new Publisher();
		publisher.setName(request.getParameter("name"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validatePublisher(request, publisher, errors)) {
			request.setAttribute("publisher", publisher);
			request.setAttribute("errors", errors);
			
			request.getRequestDispatcher("addPublisher.jsp").forward(request, response);
			return;
		}
		
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		pubDAO.addPublisher(publisher);
		
		response.sendRedirect("main?action=getAllPublishers");
	}

}
