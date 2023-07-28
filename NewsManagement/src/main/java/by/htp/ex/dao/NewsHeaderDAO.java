package by.htp.ex.dao;

import by.htp.ex.bean.NewsHeader;

public interface NewsHeaderDAO {
	int create(NewsHeader newsHeader) throws DaoException;

	void updateUser(int idNews, int idUser) throws DaoException;

}
