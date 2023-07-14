package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
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

public class GoToViewNews implements Command {

	private final INewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		String local = (String) session.getAttribute(ParamName.LOCAL);

		try {
			Map<Integer, News> newsMap = service.getMapNewsByLocal(local);
			List<News> newsList = service.convertInListActiveNewsSortedByDate(newsMap);
			request.setAttribute(ParamName.NEWS_LIST, newsList);
			request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.NEWS_LIST);
			
			int id = Integer.parseInt(request.getParameter(ParamName.ID));
			service.checkContainsActiveNewsElseThrow(id, newsMap);
			
			request.setAttribute(ParamName.NEWS, newsMap.get(id));
			request.setAttribute(ParamName.MAIN_PRESENTATION, ParamName.VIEW_NEWS);
			session.setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_VIEW_NEWS + request.getParameter(ParamName.ID));
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
			
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
			
		} catch (NumberFormatException e) {
			request.setAttribute(ParamName.ERROR, 404);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
			
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}

	}

}
