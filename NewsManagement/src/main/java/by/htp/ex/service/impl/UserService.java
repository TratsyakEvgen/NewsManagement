package by.htp.ex.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.util.lock.ReentrantLockSingleton;
import by.htp.ex.util.name.LinkName;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.ParamName;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationException;
import by.htp.ex.util.validation.ValidationProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;

public class UserService implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();

	@Override
	public User signIn(String login, String password) throws ServiceException, ServiceUserExeption {

		try {
			userDataValidation.isAuthData(login, password);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}		

		User user = null;

		try {
			if ((user = userDAO.findByLogin(login)) == null) {
				throw new ServiceUserExeption(LocalName.LOGIN_NOT_FOUND);
			}

			if (!userDAO.matchPasswords(user.getId(), password)) {
				throw new ServiceUserExeption(LocalName.INCORRECT_PASSWORD);
			}
			
			if (user.getRole().equals(ParamName.DELETE)) {
				throw new ServiceUserExeption(LocalName.USER_DELETED);
			}

		} catch (DaoException e) {
			throw new ServiceException("Login failed", e);
		}
		return user;

	}

	@Override
	public void registration(User user, String login, String password, String repeatPassword) throws ServiceUserExeption, ServiceException {
		try {
			userDataValidation.isRegistrationData(user, login, password, repeatPassword);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		user.setRole(ParamName.USER);
		user.setRegisterDate(Date.valueOf(LocalDate.now()));
		
		ReentrantLock reentrantLock = ReentrantLockSingleton.getInstance();
	
		try {
			reentrantLock.lock();
			if (userDAO.isExistLogin(login)){
				throw new ServiceUserExeption(LocalName.LOGIN_EXISTS);
			}
			userDAO.createUser(user, login, getHashPassword(password));
		} catch (DaoException e) {
			throw new ServiceException("Registration failed", e);
		} finally {
			reentrantLock.unlock();
		}
	}
	
	@Override
	public void updateAccount(User user, String login, String password) throws ServiceUserExeption, ServiceException {
		try {
			userDataValidation.isUserData(user);
			userDataValidation.isAuthData(login, password);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		try {
			matchAuthData(user.getId(), login, password);
			userDAO.updateUser(user);			
		} catch (DaoException e) {
			throw new ServiceException("Update account failed", e);
		}
		
		
	}
	
	@Override
	public void updatePassword(int id, String newPassword, String repeatPassword, String login, String password) throws ServiceUserExeption, ServiceException {
		try {
			userDataValidation.isAuthData(login, password);
			userDataValidation.isPassword(newPassword);
			userDataValidation.checkMatchPasswords(newPassword, repeatPassword);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		try {
			matchAuthData(id, login, password);
			userDAO.updatePassword(id, getHashPassword(newPassword));		
		} catch (DaoException e) {
			throw new ServiceException("Update password failed", e);
		}
		
		
	}
	
	@Override
	public void deleteAccount(int id, String login, String password) throws ServiceUserExeption, ServiceException {
		try {
			userDataValidation.isAuthData(login, password);
		} catch (ValidationException e) {
			throw new ServiceUserExeption(e.getMessage());
		}
		
		try {
			matchAuthData(id, login, password);
			Map<Integer,String> roles = new HashMap<>();
			roles.put(id, ParamName.DELETE);
			userDAO.changeRole(roles);
		} catch (DaoException e) {
			throw new ServiceException("Delete account failed", e);
		}
		
		
	}
	
	
	private void matchAuthData(int id, String login, String password) throws DaoException, ServiceUserExeption {
		if (!userDAO.matchAuthData(id, login, password)) {
			throw new ServiceUserExeption(LocalName.INCORRECT_LOGIN_OR_PASSWORD);
		}
	}
	
	
	private String getHashPassword(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}

}
