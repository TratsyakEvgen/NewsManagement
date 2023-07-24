package by.htp.ex.dao;

import by.htp.ex.dao.impl.ImageDAO;
import by.htp.ex.dao.impl.NewsDAO;
import by.htp.ex.dao.impl.UserDAO;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final IUserDAO userDao = new UserDAO();
	private final INewsDAO newsDAO = new NewsDAO();
	private final IImageDAO imageDAO = new ImageDAO();

	private DaoProvider() {
	}

	public IUserDAO getUserDao() {
		return userDao;
	}

	public INewsDAO getNewsDAO() {
		return newsDAO;
	}
	
	public IImageDAO getImageDAO() {
		return imageDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}
