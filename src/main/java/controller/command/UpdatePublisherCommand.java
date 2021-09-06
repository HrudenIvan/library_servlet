package controller.command;

import javax.servlet.http.HttpServletRequest;

import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

public class UpdatePublisherCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		Publisher publisher = new Publisher();
		publisher.setId(Long.valueOf(request.getParameter("pId")));
		publisher.setName(request.getParameter("name"));
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		pubDAO.updatePublisher(publisher);
		return "main?action=getAllPublishers";
	}

}
