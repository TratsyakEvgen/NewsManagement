package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.News;

public interface INewsService {

	List<News> getActiveNewsByLocalSortedByDate(String local) throws ServiceException;





}
