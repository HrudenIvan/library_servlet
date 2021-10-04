package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Exception.DBException;

/**
 * Default command
 */
public class DefaultCommand implements Command {

	/**
	 * Forwards users to proper pages/command according to user type
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 * @throws DBException in case of db exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String currentUserType = (String) session.getAttribute("currentUserType");
		String page = "main?action=getAllBooks";
		if ("admin".equals(currentUserType)) {
			page = "main?action=prepareAdmin";
		}
		if ("librarian".equals(currentUserType)) {
			page = "main?action=prepareLibrarian";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
