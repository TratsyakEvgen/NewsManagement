package by.htp.ex.service;

import java.util.List;
import java.util.Map;

import by.htp.ex.bean.News;

public interface INewsService {

	List<News> getNewsList(String local) throws ServiceException, ServiceUserExeption;

	List<News> convertInListActiveNewsSortedByDate(Map<Integer, News> newsMap);

	void checkContainsActiveNewsElseThrow(int id, Map<Integer, News> newsMap) throws ServiceUserExeption;

	Map<Integer, News> getMapNewsByLocal(String local) throws ServiceException, ServiceUserExeption;





}
