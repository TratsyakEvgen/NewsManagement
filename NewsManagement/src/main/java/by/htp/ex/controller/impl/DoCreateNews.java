package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.bean.User;
import by.htp.ex.controller.Command;
import by.htp.ex.service.NewsHeaderService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoCreateNews implements Command {

	private final NewsHeaderService service = ServiceProvider.getInstance().getNewsHeaderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(ParamName.USER);
		
		int idAuthor = user.getId();

		try {
			int id = service.create(idAuthor);
			response.sendRedirect(LinkName.COMMAND_GO_TO_UPDATE_NEWS + LinkName.ID + id);
		} catch (ServiceException | NumberFormatException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		}

	}

}
