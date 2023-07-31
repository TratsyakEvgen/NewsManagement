package by.htp.ex.controller.impl;

import java.io.IOException;

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

public class DoDeleteImageInNews implements Command {

	private final NewsHeaderService service = ServiceProvider.getInstance().getNewsHeaderService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idImage = Integer.parseInt(request.getParameter(ParamName.ID));
			int idNews = Integer.parseInt(request.getParameter(ParamName.ID_NEWS));
			service.deleteImage(idNews, idImage);
			response.sendRedirect(LinkName.COMMAND_GO_TO_UPDATE_NEWS + LinkName.ID + idNews + LinkName.MESSAGE_DONE);
		} catch (ServiceException | NumberFormatException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		}

	}

}
