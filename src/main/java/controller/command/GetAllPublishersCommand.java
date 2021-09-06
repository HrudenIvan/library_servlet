package controller.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.DAO.PublisherDAO;
import model.DAO.PublisherDAOImpl;
import model.entity.Publisher;

public class GetAllPublishersCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		PublisherDAO pubDAO = PublisherDAOImpl.getInstance();
		List<Publisher> publishers;
		publishers = pubDAO.getAllPublishers();
		request.setAttribute("publishers", publishers);
		return "publishers.jsp";
	}

}
