package by.htp.ex.service;

public interface NewsHeaderService {

	int create(int idAuthor) throws ServiceException;

	void addImage(int idNews, int idImage) throws ServiceException, ServiceUserExeption;

	void deleteImage(int idNews, int idImage) throws ServiceException;

	void updateUser(int idNews, int idUser) throws ServiceException;

	void updateStatus(int idNews, boolean status) throws ServiceException;

}
