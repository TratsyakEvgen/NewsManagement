package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsManagement implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			List<News> newsList = service.getAll();
			request.getSession().setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_NEWS_MANAGEMENT);	
			request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.VIEW_ADMIN);
			request.setAttribute(ParamName.MAIN_PRESENTATION, ParamName.NEWS_LIST);
			request.setAttribute(ParamName.NEWS_LIST, newsList);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		}


	}

}
