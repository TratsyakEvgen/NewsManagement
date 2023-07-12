package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_BASE_PAGE);
		String local = (String) request.getSession().getAttribute(ParamName.LOCAL);
		if (local == null) {
			local = LocalName.EN;
			request.getSession().setAttribute(ParamName.LOCAL,local);
		}
		
		String menuPresentation = (String) request.getSession().getAttribute(ParamName.MENU_PRESENTATION);
		if (menuPresentation == null) {
			request.getSession().setAttribute(ParamName.MENU_PRESENTATION, ParamName.NEWS_LIST);
		}
		
		String mainPresentation = (String) request.getSession().getAttribute(ParamName.MAIN_PRESENTATION);
		if (mainPresentation == null) {
			request.getSession().setAttribute(ParamName.MAIN_PRESENTATION, ParamName.START_PAGE);
		}


		try {
			List<News> newsList = service.getNewsList(local);
			request.setAttribute(ParamName.NEWS_LIST, newsList);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (ServiceUserExeption e) {
			request.setAttribute(ParamName.ERROR, e.getMessage());
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		}

	}

}
