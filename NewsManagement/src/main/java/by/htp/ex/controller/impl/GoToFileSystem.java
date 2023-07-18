package by.htp.ex.controller.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

import by.htp.ex.controller.Command;
import by.htp.ex.service.IFileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.util.lock.ReentrantLockSingleton;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToFileSystem implements Command{
	
	private IFileSystemService service = ServiceProvider.getInstance().getFileSystemService();
	private ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String part = request.getServletContext().getRealPath("");
		String dir = request.getServletContext().getRealPath("/uploaded-files/html/");		
		
		reentrantLock.lock();
		try {
			List<String> files = service.getAllFiles(part, dir);
			request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.VIEW_ADMIN);
			request.setAttribute(ParamName.MAIN_PRESENTATION, ParamName.VIEW_FILES);
			request.setAttribute(ParamName.FILES, files);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} finally {
			reentrantLock.unlock();
		}
		
	}

}
