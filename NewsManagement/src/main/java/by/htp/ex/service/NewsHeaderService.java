package by.htp.ex.service;

public interface NewsHeaderService {

	int create(int idAuthor) throws ServiceException;

	void udateUser(int idNews, int idUser) throws ServiceException, ServiceUserExeption;

	void addImage(int idNews, int idImage) throws ServiceException, ServiceUserExeption;

	void deleteImage(int idNews, int idImage) throws ServiceException;

}
