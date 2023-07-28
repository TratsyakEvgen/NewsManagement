package by.htp.ex.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.NewsDAO;
import by.htp.ex.service.NewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LocalName;


public class NewsServiceImpl implements NewsService {

	private final NewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	@Override
	public List<News> getAll() throws ServiceException{
		Map<Integer, News> newsMap;
		try {
			newsMap = newsDAO.getAll();
		} catch (DaoException e) {
			throw new ServiceException("Can't get all news", e);
		}
		if (newsMap != null) {
			return new ArrayList<>(newsMap.values()).stream()
					.sorted(Comparator.comparing(News::getNewsDate))
					.collect(Collectors.toList());		
		}
		return null;
	}
	

	@Override
	public List<News> getActiveNewsByLocal(String local) throws ServiceException{
		Map<Integer, News> newsMap;
		try {
			newsMap = newsDAO.getActiveNewsByLocal(local);
		} catch (DaoException e) {
			throw new ServiceException("Can't get active news by local", e);
		}
		if (newsMap != null) {
			return new ArrayList<>(newsMap.values()).stream()
					.sorted(Comparator.comparing(News::getNewsDate))
					.collect(Collectors.toList());			
		}
		return null;
			
	}
	
	@Override
	public News getNewsByLocalContentId(int id, boolean active) throws ServiceException, ServiceUserExeption{
		News news;
		try {
			news = newsDAO.getNewsByLocalContentId(id, active);
			if (news == null) {
				throw new ServiceUserExeption(LocalName.NEWS_NOT_FOUND);
			}
		} catch (DaoException e) {
			throw new ServiceException("Can't get active news by local content id", e);
		}
		return news;		
	}
	
	@Override
	public News getNewsById(int id) throws ServiceException, ServiceUserExeption{
		News news;
		try {
			news = newsDAO.getNewsById(id);
			if (news == null) {
				throw new ServiceUserExeption(LocalName.NEWS_NOT_FOUND);
			}
		} catch (DaoException e) {
			throw new ServiceException("Can't get news by id", e);
		}
		return news;		
	}

}
