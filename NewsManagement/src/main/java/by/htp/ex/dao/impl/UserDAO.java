package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ex.bean.User;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;
import by.htp.ex.util.name.ParamName;

public class UserDAO implements IUserDAO {

	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	public UserDAO() {
	}

	private static final String QUERY_FIND_USER_BY_LOGIN = "SELECT users.id, roles.role, user_detailes.name,"
			+ " user_detailes.surname, user_detailes.email, user_detailes.register_date FROM"
			+ " users JOIN roles ON users.roles_id = roles.id JOIN user_detailes ON users.id = user_detailes.users_id"
			+ " WHERE users.login =?";

	@Override
	public User findByLogin(String login) throws DaoException {
		User user = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_FIND_USER_BY_LOGIN)) {

			statment.setString(1, login);
			try (ResultSet resultSet = statment.executeQuery()) {
				if (resultSet.next()) {
					user = new User();
					user.setId(resultSet.getInt(ParamName.ID));
					user.setRole(resultSet.getString(ParamName.ROLE));
					user.setName(resultSet.getString(ParamName.NAME));
					user.setSurname(resultSet.getString(ParamName.SURNAME));
					user.setEmail(resultSet.getString(ParamName.EMAIL));
					user.setRegisterDate(resultSet.getDate(ParamName.REGISTER_DATE));
				}
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Query to DB return error in method \"findByLogin\".", e);
		}

		return user;
	}

	private static final String QUERY_FIND_PASSWORD_BY_ID = "SELECT users.password FROM users WHERE users.id =?";

	@Override
	public boolean matchPasswords(int id, String password) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_FIND_PASSWORD_BY_ID)) {

			statment.setString(1, String.valueOf(id));
			try (ResultSet resultSet = statment.executeQuery()) {
				if (resultSet.next()) {
					return password.equals(resultSet.getString(ParamName.PASSWORD));
				}
				return false;
			}

		} catch (SQLException | ConnectionPoolException e) {
			throw new DaoException("Query to DB return error in method \"matchPasswords\".", e);
		}
	}
}