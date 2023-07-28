package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.Command;
import by.htp.ex.service.FileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.service.impl.DirectoryName;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

public class DoUploadFile implements Command {

	private FileSystemService service = ServiceProvider.getInstance().getFileSystemService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String dir = request.getParameter(ParamName.DIR);
		Part file = request.getPart(ParamName.FILE);


		try {
			String direcory = DirectoryName.valueOf(dir.toUpperCase()).getDir();
			String dirPath = request.getServletContext().getRealPath(direcory);
			
			service.create(dirPath, file);
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
		}

	}

}
