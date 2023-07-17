package by.htp.ex.controller.filter;

import java.io.IOException;

import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LocalFilter implements Filter {
	
	private String defaultLocal;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		defaultLocal = filterConfig.getInitParameter(ParamName.LOCAL);
		if (defaultLocal == null) {
			defaultLocal = LocalName.EN;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		String local = (String) session.getAttribute(ParamName.LOCAL);
		if (local == null) {
			local = defaultLocal;
			session.setAttribute(ParamName.LOCAL,local);
		}
		chain.doFilter(httpRequest, response);
	}

}
