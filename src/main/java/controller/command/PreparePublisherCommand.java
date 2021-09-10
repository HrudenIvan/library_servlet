package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Exception.DBException;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

public class PreparePublisherCommand implements Command {

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
