package by.htp.ex.service.impl;

import java.util.Date;

import by.htp.ex.bean.NewsHeader;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.NewsHeaderDAO;
import by.htp.ex.service.NewsHeaderService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LocalName;

public class NewsHeaderServiceImpl implements NewsHeaderService {

	private final NewsHeaderDAO newsHeaderDAO = DaoProvider.getInstance().getNewsHeaderDAO();

	@Override
	public int create(int idAuthor) throws ServiceException {
		int id;
		try {
			NewsHeader newsHeader = new NewsHeader();
			newsHeader.setIdUser(idAuthor);
			newsHeader.setStatus(false);
			newsHeader.setNewsDate(new Date());

			id = newsHeaderDAO.create(newsHeader);

		} catch (DaoException e) {
			throw new ServiceException("Can't create news", e);
		}
		return id;
	}

	@Override
	public void udateUser(int idNews, int idUser) throws ServiceException, ServiceUserExeption {
		try {
			newsHeaderDAO.updateUser(idNews, idUser);

		} catch (DaoException e) {
			throw new ServiceException("Can't create news", e);
		}
	}

	@Override
	public void addImage(int idNews, int idImage) throws ServiceException, ServiceUserExeption {
		try {
			if (newsHeaderDAO.isExistImageInNews(idNews, idImage)) {
				throw new ServiceUserExeption(LocalName.IMAGE_ALREADY_EXISTS);
			}
			newsHeaderDAO.addImage(idNews, idImage);

		} catch (DaoException e) {
			throw new ServiceException("Can't add image in news", e);
		}
	}

	@Override
	public void deleteImage(int idNews, int idImage) throws ServiceException {
		try {
			newsHeaderDAO.deleteImage(idNews, idImage);
		} catch (DaoException e) {
			throw new ServiceException("Can't delete image in news", e);
		}
	}

}
