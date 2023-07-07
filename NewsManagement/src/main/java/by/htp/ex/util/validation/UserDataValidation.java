package by.htp.ex.util.validation;

public interface UserDataValidation {
	boolean isLogin(String login);

	boolean isPassword(String password);

	boolean isEmail(String email);

	boolean isName(String name);

	boolean isAuthData(String login, String password);


}
