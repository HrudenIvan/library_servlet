package controller.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO.OrderDAO;
import model.DAO.OrderDAOImpl;
import model.DAO.UserDAO;
import model.DAO.UserDAOImpl;
import model.entity.BookOrder;
import model.entity.User;

public class PrepareCabinetCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAO userDAO = UserDAOImpl.getInstance();
		Long userId = (Long) request.getSession().getAttribute("currentUserId");
		User currentUser = userDAO.getUserById(userId);
		request.setAttribute("currentUser", currentUser);

		OrderDAO orderDAO = OrderDAOImpl.getInstance();
		List<BookOrder> bookOrders = orderDAO.getUserOrders(userId);
		request.setAttribute("bookOrders", bookOrders);

		request.getRequestDispatcher("privateCabinet.jsp").forward(request, response);
	}

}
