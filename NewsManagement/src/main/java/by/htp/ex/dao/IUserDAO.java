package by.htp.ex.dao;

import by.htp.ex.bean.User;

public interface IUserDAO {	
	User findByLogin(String login) throws DaoException;
	boolean matchPasswords(int id, String password) throws DaoException;
	boolean isExistLogin(String login) throws DaoException;
	void createUser(User user, String login, String password) throws DaoException;
}
