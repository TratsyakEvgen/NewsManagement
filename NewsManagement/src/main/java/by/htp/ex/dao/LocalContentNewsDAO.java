package by.htp.ex.dao;

import by.htp.ex.bean.LocalContentNews;

public interface LocalContentNewsDAO {

	int getIdLocalContentByIdNewsAndLocal(int idNews, String local) throws DaoException;

	void update(LocalContentNews localContentNews) throws DaoException;

	void create(LocalContentNews localContentNews) throws DaoException;

	void delete(int id) throws DaoException;

}
