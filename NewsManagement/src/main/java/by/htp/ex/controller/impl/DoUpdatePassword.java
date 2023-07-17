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

public class DoUpdatePassword implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(ParamName.LOGIN);
		String password = request.getParameter(ParamName.PASSWORD);

		String newPassword = request.getParameter(ParamName.NEW_PASSWORD);
		String repeatPassword = request.getParameter(ParamName.REPEAT_PASSWORD);

		HttpSession session = request.getSession();
		User userSession = (User) session.getAttribute(ParamName.USER);
		Integer id = userSession.getId();

		try {
			service.updatePassword(id, newPassword, repeatPassword, login, password);
			response.sendRedirect(LinkName.COMMAND_GO_TO_ACCOUNT + LinkName.MESSAGE_DONE);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			mapAttrError.put(ParamName.NEW_PASSWORD, newPassword);
			mapAttrError.put(ParamName.REPEAT_PASSWORD, repeatPassword);
			mapAttrError.put(ParamName.MARKER, ParamName.UPDATE_PASSWORD);
			mapAttrError.put(ParamName.LOGIN, login);
			mapAttrError.put(ParamName.PASSWORD, password);
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}

	}

}
