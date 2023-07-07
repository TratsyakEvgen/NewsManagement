package by.htp.ex.service;

import by.htp.ex.bean.User;

public interface IUserService {
	
	User signIn(String login, String password) throws ServiceException, ServiceUserExeption;
	boolean registration(User user, String repeatPassword) throws ServiceUserExeption;

}
