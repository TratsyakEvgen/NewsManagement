package by.htp.ex.service.impl;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import by.htp.ex.bean.Image;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ImageDAO;
import by.htp.ex.service.ImageService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;
import by.htp.ex.util.lock.ReentrantLockSingleton;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.validation.LinkDataValidation;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.ValidationProvider;

public class ImageServiceImpl implements ImageService{
	
	private final ImageDAO imageDAO =  DaoProvider.getInstance().getImageDAO();
	private final LinkDataValidation linkDataValidation = ValidationProvider.getInstance().getLinkDataValidation();
	
	@Override
	public List<Image> getAllImages() throws ServiceException{
		List<Image> images = null;
		try {
			images = imageDAO.getAll();
		} catch (DaoException e) {
			throw new ServiceException("Can't get all images", e);
		}
		return images;
		
	}
	
	@Override
	public void add(String link) throws ServiceException, ServiceUserExeption{
		try {
			linkDataValidation.isLink(link);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();
		reentrantLock.lock();
		try {			
			if (imageDAO.findByLink(link) != 0) {
				throw new ServiceUserExeption(LocalName.LINK_EXISTS);
			}
			
			Image image = new Image();
			image.setLink(link);
			image.setStatus(true);
			
			imageDAO.create(image);
			
		} catch (DaoException e) {
			throw new ServiceException("Add image failed", e);
		} finally {
			reentrantLock.unlock();
		}
	}
	
	@Override
	public void update(Image image) throws ServiceException, ServiceUserExeption{
		try {
			linkDataValidation.isLink(image.getLink());
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();
		reentrantLock.lock();
		try {	
			int id = imageDAO.findByLink(image.getLink());
			if ((id != 0) && (id != image.getId())) {
				throw new ServiceUserExeption(LocalName.LINK_EXISTS);
			}
			
			imageDAO.update(image);
			
		} catch (DaoException e) {
			throw new ServiceException("Update image failed", e);
		} finally {
			reentrantLock.unlock();
		}
	}


}
