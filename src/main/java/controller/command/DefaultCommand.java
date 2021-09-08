package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefaultCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String currentUserType = (String) session.getAttribute("currentUserType");
		String page = "main?action=getAllBooks";
		if ("admin".equals(currentUserType)) {
			page = "admin.jsp";
		}
		if ("librarian".equals(currentUserType)) {
			page = "main?action=prepareLibrarian";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
	}

}
