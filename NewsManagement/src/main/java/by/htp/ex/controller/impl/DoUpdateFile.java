package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import by.htp.ex.controller.Command;
import by.htp.ex.service.IFileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.lock.ReentrantLockSingleton;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class DoUpdateFile implements Command {

	private IFileSystemService service = ServiceProvider.getInstance().getFileSystemService();
	private ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String dir = request.getParameter(ParamName.DIR);		
		String link = (String) request.getParameter(ParamName.LINK);		
		String dirPath = request.getServletContext().getRealPath(link);
		Part file = request.getPart(ParamName.FILE);

		reentrantLock.lock();
		try {
			service.update(dirPath, file);
			response.sendRedirect(LinkName.COMMAND_GO_TO_FILE_SYSTEM + LinkName.DIR + dir + LinkName.MESSAGE_DONE);
		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			session.setAttribute(ParamName.DIR, dir);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		} finally {
			reentrantLock.unlock();
		}

	}

}
