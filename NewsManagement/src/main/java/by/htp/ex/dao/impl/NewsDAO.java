package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.ex.bean.Image;
import by.htp.ex.bean.News;
import by.htp.ex.bean.User;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;


public class NewsDAO implements INewsDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	public NewsDAO() {
	}

	private static final String QUERY_GET_ALL_NEWS_BY_LOCAL = "SELECT news.id, news.news_date, news.status, user_detailes.name, user_detailes.surname,"
			+ " news_detailes.title, news_detailes.link, images.id, images.link, local.local FROM news "
			+ "JOIN news_detailes ON news.id = news_detailes.news_id JOIN local "
			+ "ON news_detailes.local_id = local.id JOIN user_detailes "
			+ "ON user_detailes.users_id = news.users_id LEFT JOIN images_in_news "
			+ "ON news.id = images_in_news.news_id LEFT JOIN images "
			+ "ON images_in_news.images_id = images.id  WHERE local.local = ?";

	@Override
	public Map<Integer, News> getListNewsByLocal(String local) throws NewsDAOException {

		Map<Integer, News> result = null;

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_GET_ALL_NEWS_BY_LOCAL)) {

			statment.setString(1, local);
			try (ResultSet resultSet = statment.executeQuery()) {
				result = convertResultSetInList(resultSet);
			}
		} catch (SQLException e) {
			throw new NewsDAOException("SQLException get all news by local.", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Сonnection setup error get all news  news by local.", e);
		}

		return result;
	}

	private static final String QUERY_NEWS_BY_ID_AND_LOCAL = "SELECT news.id, news.news_date, news.status, user_detailes.name, user_detailes.surname,"
			+ " news_detailes.title, news_detailes.link, images.id, images.link, local.local FROM news "
			+ "JOIN news_detailes ON news.id = news_detailes.news_id JOIN local "
			+ "ON news_detailes.local_id = local.id JOIN user_detailes "
			+ "ON user_detailes.users_id = news.users_id LEFT JOIN images_in_news "
			+ "ON news.id = images_in_news.news_id LEFT JOIN images "
			+ "ON images_in_news.images_id = images.id  WHERE news.id = ? AND local.local = ?";

	@Override
	public News findNewsByIdAndLocal(String id, String local) throws NewsDAOException {
		News news = null;
		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_NEWS_BY_ID_AND_LOCAL)) {
			statment.setString(1, id);
			statment.setString(2, local);
			try (ResultSet resultSet = statment.executeQuery()) {
				news = convertResultSetInNews(resultSet);
			}
		} catch (SQLException e) {
			throw new NewsDAOException("SQLException find news by id and local.", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Сonnection setup error find news by id and local.", e);
		}
		return news;

	}

	private Map<Integer, News> convertResultSetInList(ResultSet resultSet) throws SQLException {
		Map<Integer, News> map = new HashMap<>();
		while (resultSet.next()) {
			Image image = new Image();
			image.setId(resultSet.getInt(8));
			image.setLink(resultSet.getString(9));

			int id = resultSet.getInt(1);
			if (map.containsKey(id)) {
				map.get(id).getImages().add(image);
			} else {
				News news = new News();

				news.setId(id);
				news.setNewsDate(resultSet.getTimestamp(2));
				news.setStatus(resultSet.getBoolean(3));

				User user = new User();
				user.setName(resultSet.getString(4));
				user.setSurname(resultSet.getString(5));

				news.setAuthor(user);
				news.setTitle(resultSet.getString(6));
				news.setLink(resultSet.getString(7));

				List<Image> images = new ArrayList<>();
				images.add(image);
				news.setImages(images);

				news.setLocal(resultSet.getString(10));

				map.put(id, news);
			}
		}
		return map;
	}

	private News convertResultSetInNews(ResultSet resultSet) throws SQLException {
		News news = null;
		while (resultSet.next()) {
			news = new News();
			news.setId(resultSet.getInt(1));
			news.setNewsDate(resultSet.getTimestamp(2));
			news.setStatus(resultSet.getBoolean(3));

			User user = new User();
			user.setName(resultSet.getString(4));
			user.setSurname(resultSet.getString(5));

			news.setAuthor(user);
			news.setTitle(resultSet.getString(6));
			news.setLink(resultSet.getString(7));

			Image image = new Image();
			image.setId(resultSet.getInt(8));
			image.setLink(resultSet.getString(9));
			if (news.getImages() == null) {
				news.setImages(new ArrayList<>());
			}
			news.getImages().add(image);

			news.setLocal(resultSet.getString(10));

		}
		return news;
	}

}
