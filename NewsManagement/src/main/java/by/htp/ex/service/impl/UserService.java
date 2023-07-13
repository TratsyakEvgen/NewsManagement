package by.htp.ex.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.concurrent.locks.ReentrantLock;

import org.mindrot.jbcrypt.BCrypt;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.util.lock.ReentrantLockSingleton;
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

		} catch (DaoException e) {
			throw new ServiceException("Login failed", e);
		}
		return user;

	}

	@Override
	public boolean registration(User user, String login, String password, String repeatPassword) throws ServiceUserExeption, ServiceException {
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

		return true;
	}
	
	private String getHashPassword(String password) {
		String salt = BCrypt.gensalt();
		return BCrypt.hashpw(password, salt);
	}

}
