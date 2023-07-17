package by.htp.ex.service;

import by.htp.ex.bean.User;

public interface IUserService {

	User signIn(String login, String password) throws ServiceException, ServiceUserExeption;

	void registration(User user, String login, String password, String repeatPassword)
			throws ServiceUserExeption, ServiceException;

	void updateAccount(User user, String login, String password) throws ServiceUserExeption, ServiceException;

	void updatePassword(int id, String newPassword, String repeatPassword, String login, String password)
			throws ServiceUserExeption, ServiceException;

	void deleteAccount(int id, String login, String password) throws ServiceUserExeption, ServiceException;

}
