package by.htp.ex.dao;

import java.util.List;
import java.util.Map;

import by.htp.ex.bean.User;

public interface IUserDAO {	
	User findByLogin(String login) throws DaoException;
	boolean matchPasswords(int id, String password) throws DaoException;
	boolean isExistLogin(String login) throws DaoException;
	void createUser(User user, String login, String password) throws DaoException;
	boolean matchAuthData(int id, String login, String password) throws DaoException;
	void updateUser(User user) throws DaoException;
	void updatePassword(int id, String password) throws DaoException;
	void changeRole(Map<Integer, String> roles) throws DaoException;
	List<User> getAll() throws DaoException;
}
