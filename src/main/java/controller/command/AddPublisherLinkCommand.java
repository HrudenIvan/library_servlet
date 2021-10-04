package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Publisher;

/**
 * Command to access {@link Publisher} adding page
 */
public class AddPublisherLinkCommand implements Command {

	/**
	 * Forwards to {@link Publisher} adding page
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("addPublisher.jsp").forward(request, response);
	}

}
