package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSIgnIn implements Command {

	private final IUserService service = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(ParamName.LOGIN);
		String password = request.getParameter(ParamName.PASSWORD);

		User user;
		try {
			user = service.signIn(login, password);
			request.getSession().setAttribute(ParamName.USER, user);
			request.getSession().setAttribute(ParamName.MENU_PRESENTATION, ParamName.NEWS_LIST);
			response.sendRedirect(LinkName.COMMAND_GO_TO_BASE_PAGE);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (ServiceUserExeption e) {
			request.setAttribute(ParamName.ERROR, e.getMessage());
			request.setAttribute(ParamName.LOGIN, login);
			request.setAttribute(ParamName.PASSWORD, password);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_AUTHENTICATION).forward(request, response);
		}

	}

}
