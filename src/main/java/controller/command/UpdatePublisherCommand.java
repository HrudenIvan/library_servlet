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

public class UpdatePublisherCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Publisher publisher = new Publisher();
		publisher.setId(Long.valueOf(request.getParameter("pId")));
		publisher.setName(request.getParameter("name"));
		
		HashMap<String, String> errors = new HashMap<String, String>();
		if (!Validator.validatePublisher(publisher, errors)) {
			request.setAttribute("errors", errors);
			request.setAttribute("publisher", publisher);
			
			request.getRequestDispatcher("updatePublisher.jsp").forward(request, response);
		}
		
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		pubDAO.updatePublisher(publisher);
		
		response.sendRedirect("main?action=getAllPublishers");
	}

}
