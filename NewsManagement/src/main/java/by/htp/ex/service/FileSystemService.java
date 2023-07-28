package by.htp.ex.service;

import java.util.List;

import jakarta.servlet.http.Part;

public interface FileSystemService {

	List<String> getAllFiles(String part, String dir) throws ServiceException;

	void create(String dir, Part file) throws ServiceException, ServiceUserExeption;

	void update(String dir, Part file) throws ServiceException, ServiceUserExeption;

	void delete(String dir) throws ServiceException, ServiceUserExeption;

}
