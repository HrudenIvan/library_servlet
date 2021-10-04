package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command to access registration page
 */
public class RegisterCommand implements Command {

	/**
	 * Forwards to register page
 	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
	}

}
