package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.bean.LocalContentNews;
import by.htp.ex.controller.Command;
import by.htp.ex.service.LocalContetntNewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoUpdateContent implements Command{
	
	private final LocalContetntNewsService service = ServiceProvider.getInstance().getLocalContetntNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		String title = request.getParameter(ParamName.TITLE);
		String link = request.getParameter(ParamName.LINK);
		String local = request.getParameter(ParamName.LOCAL);
		try {
			id = Integer.parseInt(request.getParameter(ParamName.ID));
			int idNews = Integer.parseInt(request.getParameter(ParamName.ID_NEWS));
			
			
			LocalContentNews localContentNews = new LocalContentNews();
			localContentNews.setId(id);
			localContentNews.setIdNews(idNews);
			localContentNews.setTitle(title);
			localContentNews.setLink(link);
			localContentNews.setLocal(local);	
			
			service.update(localContentNews);
			
			response.sendRedirect(LinkName.COMMAND_GO_TO_UPDATE_NEWS + LinkName.ID + idNews+LinkName.MESSAGE_DONE);
		} catch (ServiceException | NumberFormatException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			mapAttrError.put(ParamName.MARKER, id);
			mapAttrError.put(ParamName.LINK, link);
			mapAttrError.put(ParamName.TITLE, title);
			mapAttrError.put(ParamName.LOCAL, local);
			request.getSession().setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}
		
	}

}
