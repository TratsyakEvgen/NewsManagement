package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {
	
	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		User user = new User();
		user.setLogin(request.getParameter(ParamName.LOGIN));
		user.setPassword(request.getParameter(ParamName.PASSWORD));
		user.setEmail(request.getParameter(ParamName.EMAIL));
		user.setName(request.getParameter(ParamName.NAME));
		user.setSurname(request.getParameter(ParamName.SURNAME));
		
		try {
			service.registration(user, request.getParameter(ParamName.REPEAT_PASSWORD));
		} catch (ServiceUserExeption e) {
			request.setAttribute(ParamName.ERROR, e.getMessage());
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_REGISTRATION).forward(request, response);
		}
		
		
	}

}
