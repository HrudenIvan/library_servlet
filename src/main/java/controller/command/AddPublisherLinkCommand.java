package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AddPublisherLinkCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "addPublisher.jsp";
	}

}
