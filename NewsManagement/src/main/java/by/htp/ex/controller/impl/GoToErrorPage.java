package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.Command;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class GoToErrorPage implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(ParamName.GO_TO_BACK, null);		
		Map<String, Object> mapAttrError = new HashMap<>();
		Object code = request.getAttribute(ParamName.ERROR);
		if (code == null) {
			code = 404;
		}
		mapAttrError.put(ParamName.ERROR, code);
		session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
		response.sendRedirect(LinkName.MESSAGE_ERROR);

	}

}
