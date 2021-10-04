package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Author;

/**
 * Command to access {@link Author} adding page
 */
public class AddAuthorLinkCommand implements Command {

	/**
	 * Forwards to {@link Author} adding page
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws ServletException in case of servlet exception
	 * @throws IOException in case of io exception
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.getRequestDispatcher("addAuthor.jsp").forward(request, response);
	}

}
