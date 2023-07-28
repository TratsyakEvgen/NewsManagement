package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.Image;

public interface IamgeService {

	List<Image> getAllImages() throws ServiceException;

	void add(String link) throws ServiceException, ServiceUserExeption;

	void update(Image image) throws ServiceException, ServiceUserExeption;

}
