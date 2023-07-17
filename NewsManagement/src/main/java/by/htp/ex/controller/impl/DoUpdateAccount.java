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

public class DoUpdateAccount implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ParamName.LOGIN);
		String password = request.getParameter(ParamName.PASSWORD);

		String email = request.getParameter(ParamName.EMAIL);
		String name = request.getParameter(ParamName.NAME);
		String surname = request.getParameter(ParamName.SURNAME);
		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute(ParamName.USER);
		Integer id = userSession.getId();

		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setName(name);
		user.setSurname(surname);

		try {
			service.updateAccount(user, login, password);			
			userSession.setName(name);
			userSession.setSurname(surname);
			userSession.setEmail(email);			
			response.sendRedirect(LinkName.COMMAND_GO_TO_ACCOUNT + LinkName.MESSAGE_DONE);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			mapAttrError.put(ParamName.USER, user);
			mapAttrError.put(ParamName.MARKER, ParamName.UPDATE_INFO);
			mapAttrError.put(ParamName.LOGIN, login);
			mapAttrError.put(ParamName.PASSWORD, password);
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}
	}

}
