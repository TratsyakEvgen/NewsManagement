package by.htp.ex.dao;

import by.htp.ex.bean.User;

public interface IUserDAO {	
	User findByLogin(String login) throws DaoException;
	boolean equalsPassword(int id, String password) throws DaoException;
}
