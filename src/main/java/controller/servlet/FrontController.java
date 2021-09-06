package controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.command.Command;
import controller.command.CommandFactory;
import model.PooledConnections;

@WebServlet("/main")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		PooledConnections.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = getPage(request);
		request.getRequestDispatcher(page).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = getPage(request);
		response.sendRedirect(page);
	}

	private String getPage(HttpServletRequest request) {
		String action = request.getParameter("action");
		Command command = CommandFactory.getCommand(action);
		String page;
		page = command.execute(request);
		return page;
	}
}
