package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.NewsService;
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

public class GoToUpdateNews implements Command{
	
	private final NewsService service = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();		
		
		try {
			int id = Integer.parseInt(request.getParameter(ParamName.ID));
			News news = service.getNewsById(id);
			request.getSession().setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_UPDATE_NEWS + LinkName.ID + id);	
			request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.VIEW_ADMIN);
			request.setAttribute(ParamName.MAIN_PRESENTATION, ParamName.UPDATE_NEWS);
			request.setAttribute(ParamName.NEWS, news);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		} catch (ServiceException | NumberFormatException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}
	}

}
