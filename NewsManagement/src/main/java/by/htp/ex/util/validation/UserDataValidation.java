package by.htp.ex.util.validation;

import by.htp.ex.bean.User;

public interface UserDataValidation {
	void isLogin(String login) throws ValidationException;

	void isPassword(String password) throws ValidationException;

	void isEmail(String email) throws ValidationException;

	void isName(String name) throws ValidationException;
	
	void isSurname(String surname) throws ValidationException;

	void isAuthData(String login, String password) throws ValidationException;

	void checkMatchPasswords(String password, String repeatPassword) throws ValidationException;

	void isRegistrationData(User user, String login, String password, String repeatPassword) throws ValidationException;

	void isUserData(User user) throws ValidationException;


	


}
