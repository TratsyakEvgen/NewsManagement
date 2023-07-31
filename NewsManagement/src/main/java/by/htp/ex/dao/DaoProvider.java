package by.htp.ex.dao;

import by.htp.ex.dao.impl.ImageDAOImpl;
import by.htp.ex.dao.impl.LocalContentNewsDAOImpl;
import by.htp.ex.dao.impl.NewsDAOImpl;
import by.htp.ex.dao.impl.NewsHeaderDAOImpl;
import by.htp.ex.dao.impl.UserDAOImpl;

public final class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final UserDAO userDao = new UserDAOImpl();
	private final NewsDAO newsDAO = new NewsDAOImpl();
	private final ImageDAO imageDAO = new ImageDAOImpl();
	private final NewsHeaderDAO newsHeaderDAO = new NewsHeaderDAOImpl();
	private final LocalContentNewsDAO localContentNewsDAO = new LocalContentNewsDAOImpl();

	private DaoProvider() {
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public NewsDAO getNewsDAO() {
		return newsDAO;
	}
	
	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public NewsHeaderDAO getNewsHeaderDAO() {
		return newsHeaderDAO;
	}

	public LocalContentNewsDAO getLocalContentNewsDAO() {
		return localContentNewsDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
	
	
}
