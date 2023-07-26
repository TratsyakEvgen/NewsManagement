package by.htp.ex.dao;

import java.util.Map;

import by.htp.ex.bean.News;



public interface INewsDAO {
	Map<Integer, News> getAll() throws NewsDAOException;
	Map<Integer, News> getActiveNewsByLocal(String local) throws NewsDAOException;
	News getNewsByLocalContentId(int id, boolean active) throws NewsDAOException;
}
