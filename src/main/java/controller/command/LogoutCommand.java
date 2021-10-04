package controller.command;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.Util;

/**
 * Logout command
 */
public class LogoutCommand implements Command {

	/**
	 * Invalidates session, creates new session, stores there guest data and forwards to all books page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//session.setAttribute("language", "ru");?????
		session.invalidate();
		session = request.getSession(true);
		session.setAttribute("isBlocked", Boolean.FALSE);
		session.setAttribute("currentUserId", 0);
		session.setAttribute("bookEditVis", "none");
		session.setAttribute("bookOrderVis", "none");
		session.setAttribute("currentUserType", "guest");
		Locale locale = Util.defineLocale(request);
		session.setAttribute("locale", locale);
		
		request.getRequestDispatcher("main?action=getAllBooks").forward(request, response);
	}
	
}
