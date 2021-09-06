package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

public class PreparePublisherCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		Long id = Long.valueOf(request.getParameter("pId"));
		Publisher publisher = pubDAO.getPublisher(id);
		request.setAttribute("publisher", publisher);
		return "updatePublisher.jsp";
	}

}
