package by.htp.ex.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LocalName;

public class NewsServiceImpl implements INewsService {

	private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();
	
	@Override
	public Map<Integer, News> getMapActiveNewsByLocal(String local) throws ServiceException {
		try {
			return newsDAO.getListNewsByLocal(local);
		} catch (NewsDAOException e) {
			throw new ServiceException("Can't get map ative news by local", e);
		}
	}

	@Override
	public List<News> getNewsList(String local) throws ServiceException, ServiceUserExeption{
		Map<Integer, News> newsMap = getMapActiveNewsByLocal(local);
		if (newsMap.isEmpty()) {
			throw new ServiceUserExeption(LocalName.NEWS_NOT_FOUND);
		}
		return getListNewsSortedByDate(newsMap);
		
	}

	@Override
	public List<News> getListNewsSortedByDate(Map<Integer, News> newsMap){
		List<News> newsList = new ArrayList<>(newsMap.values());
		newsList = newsList.stream().filter(News::isStatus).sorted(Comparator.comparing(News::getNewsDate))
				.collect(Collectors.toList());
		return newsList;

	}

	@Override
	public void isContainsActiveNews(int id, Map<Integer, News> newsMap) throws ServiceUserExeption {
		if (!newsMap.containsKey(id)) {
			throw new ServiceUserExeption(LocalName.NEWS_NOT_FOUND);
		}
		if (!newsMap.get(id).isStatus()) {
			throw new ServiceUserExeption(LocalName.NEWS_DELETED);
		}

	}

	

}
