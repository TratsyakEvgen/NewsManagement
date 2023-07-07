package by.htp.ex.controller;

import java.io.IOException;

import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class FrontController extends HttpServlet {
	private final static long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		execute(request, response);
	}

	private void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commandName = request.getParameter(ParamName.COMMAND);
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

}
