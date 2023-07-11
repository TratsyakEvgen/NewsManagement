package by.htp.ex.service;

import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;

public interface INewsService {

	List<News> getNewsList(String local) throws ServiceException, ServiceUserExeption;

	List<News> getListNewsSortedByDate(Map<Integer, News> newsMap);

	void isContainsActiveNews(int id, Map<Integer, News> newsMap) throws ServiceUserExeption;

	Map<Integer, News> getMapActiveNewsByLocal(String local) throws ServiceException;





}
