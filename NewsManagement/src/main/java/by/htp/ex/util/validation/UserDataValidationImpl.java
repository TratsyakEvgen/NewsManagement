package by.htp.ex.util.validation;

import by.htp.ex.util.name.RegExName;

public class UserDataValidationImpl implements UserDataValidation {

	@Override
	public boolean isLogin(String login) {
		return login.matches(RegExName.LOGIN);
	}

	@Override
	public boolean isPassword(String password) {
		return password.matches(RegExName.PASSWORD);
	}

	@Override
	public boolean isEmail(String email) {

		return false;
	}

	@Override
	public boolean isName(String name) {

		return false;
	}

	@Override
	public boolean isAuthData(String login, String password) {
		return (isLogin(login) & isPassword(password));
	}

}
