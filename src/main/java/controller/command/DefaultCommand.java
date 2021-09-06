package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String currentUserType = (String) session.getAttribute("currentUserType");
		String page = "main?action=getAllBooks";
		if ("admin".equals(currentUserType)) {
			page = "admin.jsp";
		}
		if ("librarian".equals(currentUserType)) {
			page = "main?action=prepareLibrarian";
		}
		return page;
	}

}
