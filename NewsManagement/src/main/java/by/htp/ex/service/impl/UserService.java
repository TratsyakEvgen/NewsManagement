package by.htp.ex.service.impl;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceUserExeption;

public class UserService implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
	// private final UserDataValidation userDataValidation =
	// ValidationProvider.getInstance().getUserDataValidation();

	@Override
	public User signIn(String login, String password) throws ServiceException, ServiceUserExeption {

		// if (!userDataValidation.isAuthData(login, password)) {
		// throw new ServiceException(ErrorName.VALIDATION_AUTHENTICATION_DATA + login +
		// " " + password);
		// }

		User user = null;

		try {

			if ((user = userDAO.findByLogin(login)) == null) {
				throw new ServiceUserExeption(LocalName.LOGIN_NOT_FOUND);
			}
			
			if (!userDAO.equalsPassword(user.getId(), password)) {
				throw new ServiceUserExeption(LocalName.INCORRECT_PASSWORD);
			}		
			

		} catch (DaoException e) {
			throw new ServiceException("Login failed", e);
		}
		return user;

	}

	@Override
	public boolean registration(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
