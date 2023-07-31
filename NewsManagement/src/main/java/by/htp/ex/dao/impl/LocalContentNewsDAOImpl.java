package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.htp.ex.bean.LocalContentNews;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.LocalContentNewsDAO;
import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;
import by.htp.ex.util.name.ParamName;

public class LocalContentNewsDAOImpl implements LocalContentNewsDAO{
	private ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	
	private static final String QUERY_FIND_BY_ID_NEWS_AND_LOCAL = "SELECT news_detailes.id FROM news_detailes"
			+ " WHERE news_detailes.news_id =  ? AND news_detailes.local_id = (SELECT local.id FROM local WHERE local.local = ?)";
	
	@Override
	public int getIdLocalContentByIdNewsAndLocal(int idNews, String local) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_FIND_BY_ID_NEWS_AND_LOCAL)) {
			statment.setInt(1, idNews);
			statment.setString(2, local);
			try (ResultSet resultSet = statment.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(ParamName.ID);
				}
				return 0;
			}
		} catch (SQLException e) {
			throw new DaoException("SQL Exception get id local content by id news and local", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error get id local content by id news and local", e);
		}
		
	}
	
	private static final String QUERY_UPDATE = "UPDATE news_detailes SET news_detailes.title = ?, "
			+ "news_detailes.link = ?, news_detailes.local_id = (SELECT local.id FROM local WHERE local.local = ?) "
			+ "WHERE news_detailes.id =  ?";
	
	@Override
	public void update(LocalContentNews localContentNews) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_UPDATE)) {
			statment.setString(1, localContentNews.getTitle());
			statment.setString(2, localContentNews.getLink());
			statment.setString(3, localContentNews.getLocal());
			statment.setInt(4, localContentNews.getId());
			statment.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("SQL Exception update local content", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error update local content", e);
		}
		
	}
	
	
	private static final String QUERY_INSERT = "INSERT INTO news_detailes (news_detailes.news_id, news_detailes.title, "
			+ "news_detailes.link, news_detailes.local_id) "
			+ "VALUE (?,?,?, (SELECT local.id FROM local WHERE local.local = ?))";
	
	@Override
	public void create(LocalContentNews localContentNews) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_INSERT)) {
			System.out.println(localContentNews);
			statment.setInt(1, localContentNews.getIdNews());
			statment.setString(2, localContentNews.getTitle());
			statment.setString(3, localContentNews.getLink());
			statment.setString(4, localContentNews.getLocal());
			statment.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("SQL Exception create local content", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error create local content", e);
		}
		
	}
	
	
	private static final String QUERY_DELETE = "DELETE FROM news_detailes WHERE news_detailes.id = ?";
	
	@Override
	public void delete(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_DELETE)) {
			statment.setInt(1, id);
			statment.executeUpdate();
		} catch (SQLException e) {
			throw new DaoException("SQL Exception delete local content", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error delete local content", e);
		}
		
	}

}
