package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ParamName.GO_TO_BACK,
				LinkName.COMMAND_GO_TO_VIEW_NEWS + request.getParameter(ParamName.ID));
		String local = (String) request.getSession().getAttribute(ParamName.LOCAL);
		int id = Integer.parseInt(request.getParameter(ParamName.ID));

		Map<Integer, News> newsMap = null;
		try {
			newsMap = service.getMapNewsByLocal(local);
			List<News> newsList = service.convertInListActiveNewsSortedByDate(newsMap);
			request.setAttribute(ParamName.NEWS_LIST, newsList);
			request.getSession().setAttribute(ParamName.MENU_PRESENTATION, ParamName.NEWS_LIST);
			service.checkContainsActiveNewsElseThrow(id, newsMap);
			request.setAttribute(ParamName.NEWS, newsMap.get(id));
			request.getSession().setAttribute(ParamName.MAIN_PRESENTATION, ParamName.VIEW_NEWS);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);			
		} catch (ServiceException e) {
			throw new ServletException(e);
		} catch (ServiceUserExeption e) {
			request.setAttribute(ParamName.ERROR, e.getMessage());
			request.getSession().setAttribute(ParamName.MAIN_PRESENTATION, ParamName.EMPTINESS);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		}
		
	}

}
