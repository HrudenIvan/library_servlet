package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.GuestNavigation;

public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("currentUserId", 0);
		session.setAttribute("currentUserType", "guest");
		session.setAttribute("navigation", GuestNavigation.getInstance());
		
		request.getRequestDispatcher("main?action=getAllBooks").forward(request, response);
	}

}
