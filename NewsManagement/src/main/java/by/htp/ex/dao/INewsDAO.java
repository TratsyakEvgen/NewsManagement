package by.htp.ex.dao;

import java.util.Map;

import by.htp.ex.bean.News;



public interface INewsDAO {
	Map<Integer, News> getListNewsByLocal(String local) throws NewsDAOException;

	News findNewsByIdAndLocal(String id, String local) throws NewsDAOException;
}
