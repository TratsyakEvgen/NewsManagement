package by.htp.ex.service;

import java.util.List;

public interface IFileSystemService {

	List<String> getAllFiles(String part, String dir) throws ServiceException;

}
