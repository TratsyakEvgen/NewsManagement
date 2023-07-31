package by.htp.ex.service.impl;

import by.htp.ex.bean.LocalContentNews;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.LocalContentNewsDAO;
import by.htp.ex.service.LocalContetntNewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.validation.LinkDataValidation;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.ValidationProvider;

public class LocalContentNewsServiceImpl implements LocalContetntNewsService {

	private final LinkDataValidation linkDataValidation = ValidationProvider.getInstance().getLinkDataValidation();
	private final LocalContentNewsDAO localContentNewsDAO = DaoProvider.getInstance().getLocalContentNewsDAO();

	@Override
	public void update(LocalContentNews localContentNews) throws ServiceUserExeption, ServiceException {
		try {
			linkDataValidation.isLink(localContentNews.getLink());
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}

		try {
			int id = localContentNewsDAO.getIdLocalContentByIdNewsAndLocal(localContentNews.getIdNews(),
					localContentNews.getLocal());

			if (id != localContentNews.getId()) {
				throw new ServiceUserExeption(LocalName.LOCAL_ALREADY_EXISTS);
			}

			localContentNewsDAO.update(localContentNews);
		} catch (DaoException e) {
			throw new ServiceException("Can't update local content", e);
		}
	}

	@Override
	public void create(LocalContentNews localContentNews) throws ServiceUserExeption, ServiceException {
		try {
			linkDataValidation.isLink(localContentNews.getLink());
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}

		try {
			int id = localContentNewsDAO.getIdLocalContentByIdNewsAndLocal(localContentNews.getIdNews(),
					localContentNews.getLocal());

			if (id != 0) {
				throw new ServiceUserExeption(LocalName.LOCAL_ALREADY_EXISTS);
			}

			localContentNewsDAO.create(localContentNews);
		} catch (DaoException e) {
			throw new ServiceException("Can't create local content", e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			localContentNewsDAO.delete(id);;
		} catch (DaoException e) {
			throw new ServiceException("Can't delete local content", e);
		}
	}

}
