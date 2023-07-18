package by.htp.ex.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.htp.ex.service.IFileSystemService;
import by.htp.ex.service.ServiceException;

public class FileSystemService implements IFileSystemService{
	
	@Override
	public List<String> getAllFiles(String part, String dir) throws ServiceException{
		Path pathPart = Paths.get(part);	
		System.out.println(part);
		System.out.println(dir);
		List<String> files = null;
		try {
		    try (Stream<Path> paths = Files.walk(Paths.get(dir)))
		    {
		    	files = paths.filter(Files::isRegularFile).map(p -> pathPart.relativize(p).toString()).collect(Collectors.toList());
		    	files.forEach(System.out::println);
		    }
		} catch (IOException e) {
		    throw new ServiceException("IO Exception get all files " + dir, e);
		}
		return files;
		
	}

}
