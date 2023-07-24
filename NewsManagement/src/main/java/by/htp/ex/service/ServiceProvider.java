package by.htp.ex.service;

import by.htp.ex.service.impl.FileSystemService;
import by.htp.ex.service.impl.ImageService;
import by.htp.ex.service.impl.NewsService;
import by.htp.ex.service.impl.UserService;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private ServiceProvider() {
	}

	private final IUserService userService = new UserService();
	private final INewsService newsService = new NewsService();
	private final IFileSystemService fileSystemService = new FileSystemService();
	private final IIamgeService iamgeService = new ImageService();

	public INewsService getNewsService() {
		return newsService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public IFileSystemService getFileSystemService() {
		return fileSystemService;
	}
	
	public IIamgeService getIamgeService() {
		return iamgeService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	
	
	

}
