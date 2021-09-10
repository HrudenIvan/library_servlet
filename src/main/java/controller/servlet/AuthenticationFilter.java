package controller.servlet;

import java.io.IOException;
import java.util.EnumSet;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import controller.command.CommandEnum;

import static controller.command.CommandEnum.*;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
	private EnumSet<CommandEnum> guestCommands;
	private EnumSet<CommandEnum> userCommands;
	private EnumSet<CommandEnum> librarianCommands;
	private EnumSet<CommandEnum> adminCommands;
	
	public AuthenticationFilter() {
		guestCommands = EnumSet.of(LOGIN, GETALLBOOKS, ADDUSER, REGISTER, DEFAULT, FINDBOOKSBYTITLE, CHANGELOCALE);
		userCommands = EnumSet.of(LOGOUT, GETALLBOOKS, DEFAULT, PREPAREBOOKORDER,
				ADDBOOKORDER, PREPARECABINET, FINDBOOKSBYTITLE, CHANGELOCALE);
		librarianCommands = EnumSet.of(LOGOUT, DEFAULT, PREPARELIBRARIAN, PREPAREBOOKORDERUPDATE,
				UPDATEBOOKORDER, SUBSCRIPTION, CHANGELOCALE);
		adminCommands = EnumSet.of(LOGOUT, GETALLBOOKS, GETALLUSERS, UPDATEUSER, PREPAREUSER,
				PREPAREBOOK, UPDATEBOOK, ADDUSER, GETALLAUTHORS, PREPAREAUTHOR, UPDATEAUTHOR,
				ADDAUTHORLINK, ADDAUTHOR, ADDBOOKLINK, ADDBOOK, ADDPUBLISHERLINK, ADDPUBLISHER,
				PREPAREPUBLISHER, UPDATEPUBLISHER, GETALLPUBLISHERS, FINDBOOKSBYTITLE, DEFAULT, CHANGELOCALE);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String path = req.getServletPath();
		String action = req.getParameter("action");
		if (action == null) {
			if ("/login.jsp".equals(path) || "/register.jsp".equals(path)) {
				req.getRequestDispatcher(path).forward(request, response);
				return;
			}
			req.getRequestDispatcher("main?action=default").forward(request, response);
			return;
		}
		
		HttpSession session = req.getSession();
		String userType = (String) session.getAttribute("currentUserType");
		boolean isBlocked = (boolean) session.getAttribute("isBlocked");
		
		CommandEnum command = CommandEnum.valueOf(action.toUpperCase());
		
		if (isBlocked && command != CommandEnum.LOGOUT) {
			if ("user".equals(userType)) {
				req.getRequestDispatcher("main?action=prepareCabinet").forward(request, response);
				return;
			} else {
				req.getRequestDispatcher("main?action=default").forward(request, response);
				return;
			}
		}
		
		
		if ("guest".equals(userType) && !guestCommands.contains(command)) {
			req.getRequestDispatcher("main?action=default").forward(request, response);
			return;
		}
		
		if ("user".equals(userType) && !userCommands.contains(command)) {
			req.getRequestDispatcher("main?action=default").forward(request, response);
			return;
		}
		
		if ("librarian".equals(userType) && !librarianCommands.contains(command)) {
			req.getRequestDispatcher("main?action=default").forward(request, response);
			return;
		}
		
		if ("admin".equals(userType) && !adminCommands.contains(command)) {
			req.getRequestDispatcher("main?action=default").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

}
