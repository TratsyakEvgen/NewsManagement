package by.htp.ex.service;

import by.htp.ex.service.impl.FileSystemServiceImpl;
import by.htp.ex.service.impl.ImageServiceImpl;
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
	private final IamgeService iamgeService = new ImageServiceImpl();
	private final NewsHeaderService newsHeaderService = new NewsHeaderServiceImpl();

	public NewsService getNewsService() {
		return newsService;
	}

	public UserService getUserService() {
		return userService;
	}

	public FileSystemService getFileSystemService() {
		return fileSystemService;
	}
	
	public IamgeService getIamgeService() {
		return iamgeService;
	}

	public NewsHeaderService getNewsHeaderService() {
		return newsHeaderService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	
	
	

}
