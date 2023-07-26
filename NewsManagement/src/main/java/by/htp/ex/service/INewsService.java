package by.htp.ex.service;

import java.util.List;
import by.htp.ex.bean.News;

public interface INewsService {

	List<News> getActiveNewsByLocal(String local) throws ServiceException;

	List<News> getAll() throws ServiceException;

	News getNewsByLocalContentId(int id, boolean active) throws ServiceException, ServiceUserExeption;







}
