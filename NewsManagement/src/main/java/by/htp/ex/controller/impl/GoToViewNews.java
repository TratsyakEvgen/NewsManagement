package by.htp.ex.controller.impl;

import java.io.IOException;

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

public class GoToViewNews implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ParamName.GO_TO_BACK,
				LinkName.COMMAND_GO_TO_BASE_PAGE + request.getAttribute(ParamName.ID));
		request.getSession().setAttribute(ParamName.BODY_PRESENTATION, ParamName.VIEW_NEWS);
		request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
	}

}
