package controller.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;

public class AddBookOrderCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long bookId = Long.valueOf(request.getParameter("bId"));
		Long userId = Long.valueOf(request.getParameter("uId"));
		int orderTypeId = Integer.parseInt(request.getParameter("otId"));
		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		orderDAO.addOrder(userId, bookId, orderTypeId);
		
		response.sendRedirect("main?action=getAllBooks");
	}

}
