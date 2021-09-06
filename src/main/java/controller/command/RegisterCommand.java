package controller.command;

import javax.servlet.http.HttpServletRequest;

public class RegisterCommand implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "register.jsp";
	}

}
