package by.htp.ex.controller.impl;

import java.io.IOException;
import by.htp.ex.controller.Command;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangeLocal implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(ParamName.LOCAL, request.getParameter(ParamName.LOCAL));
		response.sendRedirect((String) session.getAttribute(ParamName.GO_TO_BACK));

	}

}
