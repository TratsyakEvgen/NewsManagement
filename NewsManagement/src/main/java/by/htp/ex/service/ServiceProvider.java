package by.htp.ex.service;

import by.htp.ex.service.impl.FileSystemServiceImpl;
import by.htp.ex.service.impl.ImageServiceImpl;
import by.htp.ex.service.impl.LocalContentNewsServiceImpl;
import by.htp.ex.service.impl.NewsHeaderServiceImpl;
import by.htp.ex.service.impl.NewsServiceImpl;
import by.htp.ex.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {
	}

	private final UserService userService = new UserServiceImpl();
	private final NewsService newsService = new NewsServiceImpl();
	private final FileSystemService fileSystemService = new FileSystemServiceImpl();
	private final ImageService imageService = new ImageServiceImpl();
	private final NewsHeaderService newsHeaderService = new NewsHeaderServiceImpl();
	private final LocalContetntNewsService localContetntNewsService = new LocalContentNewsServiceImpl();

	public NewsService getNewsService() {
		return newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public FileSystemService getFileSystemService() {
		return fileSystemService;
	}
	
	public ImageService getImageService() {
		return imageService;
	}

	public NewsHeaderService getNewsHeaderService() {
		return newsHeaderService;
	}

	public LocalContetntNewsService getLocalContetntNewsService() {
		return localContetntNewsService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	
	
	

}
