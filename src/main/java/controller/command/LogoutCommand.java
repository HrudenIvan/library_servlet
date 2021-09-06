package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import controller.util.GuestNavigation;

public class LogoutCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("currentUserId", 0);
		session.setAttribute("currentUserType", "guest");
		session.setAttribute("navigation", GuestNavigation.getInstance());
		return "main?action=getAllBooks";
	}

}
