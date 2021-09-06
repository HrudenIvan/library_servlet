package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

public class AddPublisherCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Publisher publisher = new Publisher();
		publisher.setName(request.getParameter("name"));
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		pubDAO.addPublisher(publisher);
		return "main?action=getAllPublishers";
	}

}
