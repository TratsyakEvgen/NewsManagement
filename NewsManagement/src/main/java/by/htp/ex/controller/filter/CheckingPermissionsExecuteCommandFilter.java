package by.htp.ex.controller.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import by.htp.ex.bean.User;
import by.htp.ex.controller.CommandName;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CheckingPermissionsExecuteCommandFilter implements Filter {

	private final Set<CommandName> commandsGuest = new HashSet<>();
	private final Set<CommandName> commandsUser = new HashSet<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		commandsGuest.add(CommandName.CHANGE_LOCAL);
		commandsGuest.add(CommandName.DO_REGISTRATION);
		commandsGuest.add(CommandName.DO_SIGN_IN);
		commandsGuest.add(CommandName.GO_TO_AUTHENTICATION);
		commandsGuest.add(CommandName.GO_TO_BASE_PAGE);
		commandsGuest.add(CommandName.GO_TO_ERROR_PAGE);
		commandsGuest.add(CommandName.GO_TO_REGISTRATION);

		commandsUser.add(CommandName.DO_DELETE_ACCOUNT);
		commandsUser.add(CommandName.DO_SIGN_OUT);
		commandsUser.add(CommandName.DO_UPDATE_ACCOUNT);
		commandsUser.add(CommandName.DO_UPDATE_PASSWORD);
		commandsUser.add(CommandName.GO_TO_ACCOUNT);
		commandsUser.add(CommandName.GO_TO_VIEW_NEWS);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		
		String command = httpRequest.getParameter(ParamName.COMMAND);
		CommandName commandName = null;
		try {
			commandName = CommandName.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
			return;
		}	
		
		
		User user = (User) session.getAttribute(ParamName.USER);
		if ((user == null && !commandsGuest.contains(commandName))
				|| (user != null && user.getRole().equals(ParamName.USER)
				&& !commandsGuest.contains(commandName) && !commandsUser.contains(commandName))) {
			
			request.setAttribute(ParamName.ERROR, 401);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
			
		} else {
			chain.doFilter(request, response);
		}
	}

}
