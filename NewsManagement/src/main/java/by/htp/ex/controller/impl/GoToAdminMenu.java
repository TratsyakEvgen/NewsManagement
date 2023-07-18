package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.Command;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToAdminMenu implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute(ParamName.GO_TO_BACK, LinkName.COMMAND_GO_TO_ADMIN_MENU);
		request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.VIEW_ADMIN);
		request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);		
	}

}
