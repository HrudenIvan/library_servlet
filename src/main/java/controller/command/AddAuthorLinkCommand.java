package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AddAuthorLinkCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "addAuthor.jsp";
	}

}
