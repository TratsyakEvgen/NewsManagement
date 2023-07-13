package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoRegistration implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter(ParamName.LOGIN);
		String password = request.getParameter(ParamName.PASSWORD);
		String email = request.getParameter(ParamName.EMAIL);
		String name = request.getParameter(ParamName.NAME);
		String surname = request.getParameter(ParamName.SURNAME);
		String repeatPassword = request.getParameter(ParamName.REPEAT_PASSWORD);
		HttpSession session = request.getSession();		
		
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setSurname(surname);

		try {
			service.registration(user, login, password, repeatPassword);
			response.sendRedirect(LinkName.COMMAND_GO_TO_REGISTRATION + LinkName.MESSAGE_DONE);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			mapAttrError.put(ParamName.USER, user);
			mapAttrError.put(ParamName.REPEAT_PASSWORD, repeatPassword);
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);

		}
	}

}
