package by.htp.ex.dao;

import by.htp.ex.bean.NewsHeader;

public interface NewsHeaderDAO {
	int create(NewsHeader newsHeader) throws DaoException;

	void updateUser(int idNews, int idUser) throws DaoException;

	void addImage(int idNews, int idImage) throws DaoException;

	boolean isExistImageInNews(int idNews, int idImage) throws DaoException;

	void deleteImage(int idNews, int idImage) throws DaoException;

}
