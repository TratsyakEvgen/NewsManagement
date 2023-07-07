package by.htp.ex.util.validation;

import by.htp.ex.bean.User;
import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.RegExName;

public class UserDataValidationImpl implements UserDataValidation {

	@Override
	public void isLogin(String login) throws ValidationException {
		if (!login.matches(RegExName.LOGIN)) {
			throw new ValidationException(LocalName.LOGIN_VALIDATION);
		}
	}

	@Override
	public void isPassword(String password) throws ValidationException {
		if (!password.matches(RegExName.PASSWORD)) {
			throw new ValidationException(LocalName.PASSWORD_VALIDATION);
		}
	}

	@Override
	public void isEmail(String email) throws ValidationException {
		if (!email.matches(RegExName.EMAIL)) {
			throw new ValidationException(LocalName.EMAIL_VALIDATION);
		}
	}

	@Override
	public void isName(String name) throws ValidationException {
		if (!name.matches(RegExName.NAME)) {
			throw new ValidationException(LocalName.NAME_VALIDATION);
		}
	}
	
	@Override
	public void isSurname(String surname) throws ValidationException {		
		if (!surname.matches(RegExName.NAME)) {
			throw new ValidationException(LocalName.SURNAME_VALIDATION);
		}
	}
	
	@Override
	public void checkMatchPasswords(String password, String repeatPassword) throws ValidationException {
		if (!password.equals(repeatPassword)) {
			throw new ValidationException(LocalName.MATH_PASSWORDS_VALIDATION);
		}
	}

	@Override
	public void isAuthData(String login, String password) throws ValidationException {
		isLogin(login);
		isPassword(password);
	}	
	
	@Override
	public void isRegistrationData(User user, String repeatPassword) throws ValidationException {
		isPassword(user.getPassword());
		checkMatchPasswords(user.getPassword(), repeatPassword);
		isLogin(user.getLogin());
		isEmail(user.getEmail());
		isName(user.getName());
		isSurname(user.getSurname());
	}

}
