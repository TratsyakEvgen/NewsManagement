package by.htp.ex.controller.filter;

import java.io.IOException;
import java.util.Map;

import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CheckingForErrorFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		if (httpRequest.getParameter(ParamName.ERROR) != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> mapAttrError = (Map<String, Object>) session.getAttribute(ParamName.MAP_ATTR_ERROR);
			if (mapAttrError != null) {
				mapAttrError.entrySet().forEach(e -> httpRequest.setAttribute(e.getKey(), e.getValue()));
			}
			String link = (String) session.getAttribute(ParamName.GO_TO_BACK);
			if (link == null) {
				link = LinkName.ERROR_JSP;
			}
			httpRequest.getRequestDispatcher(link).forward(httpRequest, response);
		} else {
			if (session.getAttribute(ParamName.MAP_ATTR_ERROR) != null) {
				session.removeAttribute(ParamName.MAP_ATTR_ERROR);
			}
			chain.doFilter(httpRequest, response);
		}

	}

}
