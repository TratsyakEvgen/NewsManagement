package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import by.htp.ex.bean.Image;
import by.htp.ex.controller.Command;
import by.htp.ex.service.IIamgeService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.logger.ConsoleLogger;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.ParamName;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DoUpdateImage implements Command {

	private IIamgeService service = ServiceProvider.getInstance().getIamgeService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		int id = Integer.parseInt(request.getParameter(ParamName.ID));
		String link = request.getParameter(ParamName.LINK);
		boolean status = Boolean.parseBoolean(request.getParameter(ParamName.STATUS));

		Image image = new Image();
		image.setId(id);
		image.setLink(link);
		image.setStatus(status);

		try {
			service.update(image);
			response.sendRedirect(LinkName.COMMAND_GO_TO_GALLERY + LinkName.MESSAGE_DONE);
		} catch (ServiceException | NumberFormatException e) {
			ConsoleLogger.getInstance().warn(e);
			request.setAttribute(ParamName.ERROR, 500);
			request.getRequestDispatcher(LinkName.COMMAND_GO_TO_ERROR_PAGE).forward(request, response);
		} catch (ServiceUserExeption e) {
			Map<String, Object> mapAttrError = new HashMap<>();
			mapAttrError.put(ParamName.ERROR, e.getMessage());
			mapAttrError.put(ParamName.LINK, link);
			mapAttrError.put(ParamName.MARKER, id);
			mapAttrError.put(ParamName.STATUS, status);
			session.setAttribute(ParamName.MAP_ATTR_ERROR, mapAttrError);
			response.sendRedirect(LinkName.MESSAGE_ERROR);
		}

	}

}
