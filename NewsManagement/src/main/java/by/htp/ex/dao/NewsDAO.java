package by.htp.ex.dao;

import java.util.Map;

import by.htp.ex.bean.News;



public interface NewsDAO {
	Map<Integer, News> getAll() throws DaoException;
	Map<Integer, News> getActiveNewsByLocal(String local) throws DaoException;
	News getNewsByLocalContentId(int id, boolean active) throws DaoException;
	News getNewsById(int id) throws DaoException;
}
