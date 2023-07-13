package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.BCrypt;

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

		} catch (SQLException e) {
			throw new DaoException("SQLException find user by login.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error find user by login.", e);
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
					return BCrypt.checkpw(password, resultSet.getString(ParamName.PASSWORD));
				}
				return false;
			}

		} catch (SQLException e) {
			throw new DaoException("SQLException find password by id.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error find password by id.", e);
		}
	}
	
	
	private static final String QUERY_FIND_LOGIN = "SELECT users.login FROM users WHERE users.login =?";

	@Override
	public boolean isExistLogin(String login) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_FIND_LOGIN)) {

			statment.setString(1, String.valueOf(login));
			try (ResultSet resultSet = statment.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {
			throw new DaoException("SQLException find login.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error find login.", e);
		}
	}
	
	
	
	
	private static final String QUERY_INSERT_USERS = "INSERT INTO users (users.login, users.password, users.roles_id) VALUE (?,?,(SELECT roles.id FROM roles WHERE roles.role=?))";
	private static final String QUERY_INSERT_USER_DITAILES = "INSERT INTO user_detailes VALUE (?,?,?,?,?)";	

	@Override
	public void createUser(User user, String login, String password) throws DaoException {
		try (Connection connection = connectionPool.takeConnection()){
			connection.setAutoCommit(false);
			
			int id = 0;
			try (PreparedStatement statment = connection.prepareStatement(QUERY_INSERT_USERS, Statement.RETURN_GENERATED_KEYS)){
				
				statment.setString(1, login);
				statment.setString(2, password);
				statment.setString(3, user.getRole());
				statment.executeUpdate();					
				
				try (ResultSet resultSet = statment.getGeneratedKeys()){
					if (resultSet.next()) {
						id = resultSet.getInt(1);
					}
				}
			} catch(SQLException e){
				connection.rollback();	
				connection.setAutoCommit(true);
				throw new DaoException("Error in query INSERT users.", e);
			}
			
				
			try (PreparedStatement statmentDetailes = connection.prepareStatement(QUERY_INSERT_USER_DITAILES)){	

				statmentDetailes.setInt(1, id);
				statmentDetailes.setString(2, user.getName());
				statmentDetailes.setString(3, user.getSurname());
				statmentDetailes.setString(4, user.getEmail());
				statmentDetailes.setDate(5, (Date) user.getRegisterDate());
				statmentDetailes.executeUpdate();
				connection.commit();
			} catch(SQLException e){
				connection.rollback();
				connection.setAutoCommit(true);
				throw new DaoException("Error in query INSERT user_ditails.", e);
			}
			
		} catch (SQLException e) {
			throw new DaoException("Error commit connection.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error create user.", e);
		}

	}

}

	
	
