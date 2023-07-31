package by.htp.ex.service;

import by.htp.ex.bean.LocalContentNews;

public interface LocalContetntNewsService {

	void update(LocalContentNews localContentNews) throws ServiceUserExeption, ServiceException;

	void create(LocalContentNews localContentNews) throws ServiceUserExeption, ServiceException;

	void delete(int id) throws ServiceException;

}
