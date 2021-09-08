package controller.command;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;
import util.Validator;

public class AddPublisherCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Publisher publisher = new Publisher();
		publisher.setName(request.getParameter("name"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validatePublisher(publisher, errors)) {
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
