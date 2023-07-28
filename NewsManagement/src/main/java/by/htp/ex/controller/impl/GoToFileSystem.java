package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.controller.Command;
import by.htp.ex.service.FileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.impl.DirectoryName;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToFileSystem implements Command {

	private FileSystemService service = ServiceProvider.getInstance().getFileSystemService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dir = request.getParameter(ParamName.DIR);
		String realPath = request.getServletContext().getRealPath("");

		try {
			String directory = DirectoryName.valueOf(dir.toUpperCase()).getDir();
			String dirPath = request.getServletContext().getRealPath(directory);

			List<String> files = service.getAllFiles(realPath, dirPath);
			request.getSession().setAttribute(ParamName.GO_TO_BACK,
					LinkName.COMMAND_GO_TO_FILE_SYSTEM + LinkName.DIR + dir);
			request.setAttribute(ParamName.MENU_PRESENTATION, ParamName.VIEW_ADMIN);
			request.setAttribute(ParamName.MAIN_PRESENTATION, ParamName.VIEW_FILES);
			request.setAttribute(ParamName.FILES, files);
			request.setAttribute(ParamName.DIR, dir);
			request.getRequestDispatcher(LinkName.BASE_LAYOUT_JSP).forward(request, response);

		} catch (ServiceException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);

		} catch (IllegalArgumentException | NullPointerException e) {
			request.setAttribute(ParamName.ERROR, 404);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);

		}

	}

}
