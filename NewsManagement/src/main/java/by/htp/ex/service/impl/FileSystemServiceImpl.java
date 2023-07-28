package by.htp.ex.service.impl;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.htp.ex.service.FileSystemService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.name.LocalName;
import jakarta.servlet.http.Part;

public class FileSystemServiceImpl implements FileSystemService{
	
	@Override
	public List<String> getAllFiles(String part, String dir) throws ServiceException{
		Path pathPart = Paths.get(part);	
		List<String> files = null;
		try {
		    try (Stream<Path> paths = Files.walk(Paths.get(dir)))
		    {
		    	files = paths.filter(Files::isRegularFile).map(p -> pathPart.relativize(p).toString()).collect(Collectors.toList());
		    }
		} catch (IOException e) {
		    throw new ServiceException("IO Exception get all files " + dir, e);
		}
		return files;
		
	}
	
	@Override
	public void create(String dir, Part file) throws ServiceException, ServiceUserExeption {
		if (file.getSize() == 0) {
			throw new ServiceUserExeption(LocalName.FILE_NOT_SELECTED);
		}
		String fileName = file.getSubmittedFileName(); 
		Path path = Paths.get(dir + fileName);
		try {
			Files.createFile(path);
			Files.write(path, file.getInputStream().readAllBytes());
		} catch (FileAlreadyExistsException e) {
			throw new ServiceUserExeption(LocalName.FILE_ALREADY_EXIST);
		} catch (IOException e) {
			throw new ServiceException("IO Exception create file " + dir, e);
		}
		
	}
	
	
	@Override
	public void update(String dir, Part file) throws ServiceException, ServiceUserExeption {
		if (file.getSize() == 0) {
			throw new ServiceUserExeption(LocalName.FILE_NOT_SELECTED);
		}
		Path path = Paths.get(dir);
		if (!Files.exists(path)) {
			throw new ServiceUserExeption(LocalName.FILE_DOES_NOT_EXIST);
		}
		
		try {
			Files.write(path, file.getInputStream().readAllBytes());
		} catch (IOException e) {
			throw new ServiceException("IO Exception update file " + dir, e);
		}
		
	}
	
	@Override
	public void delete(String dir) throws ServiceException, ServiceUserExeption {
		Path path = Paths.get(dir);		
		try {
			Files.delete(path);
		} catch (NoSuchFileException e) {
			throw new ServiceUserExeption(LocalName.FILE_DOES_NOT_EXIST);
		} catch (IOException e) {
			throw new ServiceException("IO Exception delete file " + dir, e);
		}
		
	}

}
