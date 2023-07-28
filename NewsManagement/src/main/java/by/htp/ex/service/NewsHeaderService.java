package by.htp.ex.service;

public interface NewsHeaderService {

	int create(int idAuthor) throws ServiceException;

	void udateUser(int idNews, int idUser) throws ServiceException, ServiceUserExeption;

}
