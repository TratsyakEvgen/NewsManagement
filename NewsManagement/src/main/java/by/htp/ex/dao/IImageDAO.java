package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.Image;

public interface IImageDAO {

	List<Image> getAll() throws DaoException;

	void update(Image image) throws DaoException;

	int findByLink(String link) throws DaoException;

	void create(Image image) throws DaoException;

}
