package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToBasePage implements Command {

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_BASE_PAGE);
		String local = (String) request.getSession().getAttribute(ParamName.LOCAL);
		if (local == null) {
			local = "en";
			request.getSession().setAttribute(ParamName.LOCAL, local);
		}

		try {
			List<News> newsList = newsService.getActiveNewsByLocalSortedByDate(local);
			request.setAttribute(ParamName.NEWS, newsList);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		} catch (ServiceException e) {
			throw new ServletException(e);
		}

	}

}
