package by.htp.ex.dao;

import java.text.ParseException;
import java.util.List;

import by.htp.ex.bean.News;



public interface INewsDAO {
	List<News> getList() throws NewsDAOException, ParseException;
	List<News> getLatestsList(int count) throws NewsDAOException, ParseException;
	News fetchById(int id) throws NewsDAOException, ParseException;
	int addNews(News news) throws NewsDAOException;
	void updateNews(News news) throws NewsDAOException;
	void deleteNewses(String[] idNewses)throws NewsDAOException;
}
