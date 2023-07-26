package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.htp.ex.bean.Image;
import by.htp.ex.bean.LocalContentNews;
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

	private static final String QUERY_ACTIVE_NEWS_BY_LOCAL = "SELECT news.id, news.news_date, news.status, user_detailes.users_id, roles.role, "
			+ "user_detailes.name, user_detailes.surname, user_detailes.email, user_detailes.register_date, news_detailes.id, news_detailes.news_id, "
			+ "news_detailes.title, news_detailes.link, local.local, images.id, images.link, images.status FROM news "
			+ "JOIN news_detailes ON news.id = news_detailes.news_id "
			+ "JOIN local ON news_detailes.local_id = local.id "
			+ "JOIN user_detailes ON user_detailes.users_id = news.users_id "
			+ "JOIN users ON user_detailes.users_id = users.id JOIN roles ON users.roles_id = roles.id "
			+ "LEFT JOIN images_in_news ON news.id = images_in_news.news_id "
			+ "LEFT JOIN images ON images_in_news.images_id = images.id AND images.status = 1 "
			+ "WHERE local.local = ? AND news.status = 1";

	@Override
	public Map<Integer, News> getActiveNewsByLocal(String local) throws NewsDAOException {

		Map<Integer, News> result = null;

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_ACTIVE_NEWS_BY_LOCAL)) {

			statment.setString(1, local);
			try (ResultSet resultSet = statment.executeQuery()) {
				result = convertResultSetInMap(resultSet);
			}
		} catch (SQLException e) {
			throw new NewsDAOException("SQLException get all news by local.", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Сonnection setup error get all news by local.", e);
		}

		return result;
	}

	private static final String QUERY_ACTIVE_NEWS_BY_LOCAL_CONTENT_ID = "SELECT news.id, news.news_date, news.status, user_detailes.users_id, roles.role, "
			+ "user_detailes.name, user_detailes.surname, user_detailes.email, user_detailes.register_date, news_detailes.id, news_detailes.news_id, "
			+ "news_detailes.title, news_detailes.link, local.local, images.id, images.link, images.status FROM news "
			+ "JOIN news_detailes ON news.id = news_detailes.news_id "
			+ "JOIN local ON news_detailes.local_id = local.id "
			+ "JOIN user_detailes ON user_detailes.users_id = news.users_id "
			+ "JOIN users ON user_detailes.users_id = users.id JOIN roles ON users.roles_id = roles.id "
			+ "LEFT JOIN images_in_news ON news.id = images_in_news.news_id "
			+ "LEFT JOIN images ON images_in_news.images_id = images.id AND images.status = 1 "
			+ "WHERE news_detailes.id = ?";
	private static final String PATH_QUERY_ACTIVE_NEWS = " AND news.status = 1";

	@Override
	public News getNewsByLocalContentId(int id, boolean active) throws NewsDAOException {
		Map<Integer, News> result = null;
		
		String sqlQuery = QUERY_ACTIVE_NEWS_BY_LOCAL_CONTENT_ID;
		
		if (active)	{
			sqlQuery += PATH_QUERY_ACTIVE_NEWS;
		}

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(sqlQuery)) {

			statment.setInt(1, id);
			try (ResultSet resultSet = statment.executeQuery()) {
				result = convertResultSetInMap(resultSet);
			}
		} catch (SQLException e) {
			throw new NewsDAOException("SQLException get all news by local.", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Сonnection setup error get all news by local.", e);
		}

		if (result == null) {
			return null;
		}

		return result.entrySet().stream().findFirst().get().getValue();
	}

	private static final String QUERY_GET_ALL_NEWS = "SELECT news.id, news.news_date, news.status, user_detailes.users_id, roles.role, "
			+ "user_detailes.name, user_detailes.surname, user_detailes.email, user_detailes.register_date, news_detailes.id, news_detailes.news_id, "
			+ "news_detailes.title, news_detailes.link, local.local, images.id, images.link, images.status FROM news "
			+ "JOIN news_detailes ON news.id = news_detailes.news_id "
			+ "JOIN local ON news_detailes.local_id = local.id "
			+ "JOIN user_detailes ON user_detailes.users_id = news.users_id "
			+ "JOIN users ON user_detailes.users_id = users.id " + "JOIN roles ON users.roles_id = roles.id "
			+ "LEFT JOIN images_in_news ON news.id = images_in_news.news_id "
			+ "LEFT JOIN images ON images_in_news.images_id = images.id ";

	@Override
	public Map<Integer, News> getAll() throws NewsDAOException {

		Map<Integer, News> result = null;

		try (Connection connection = connectionPool.takeConnection();
				Statement statment = connection.createStatement()) {

			try (ResultSet resultSet = statment.executeQuery(QUERY_GET_ALL_NEWS)) {
				result = convertResultSetInMap(resultSet);
			}
		} catch (SQLException e) {
			throw new NewsDAOException("SQLException get all news.", e);
		} catch (ConnectionPoolException e) {
			throw new NewsDAOException("Сonnection setup error get all news.", e);
		}

		return result;
	}

	private Map<Integer, News> convertResultSetInMap(ResultSet resultSet) throws SQLException {
		Map<Integer, News> mapNews = null;
		Map<Integer, LocalContentNews> mapLocalContentNews = null;

		if (resultSet.isBeforeFirst()) {
			mapNews = new HashMap<>();
			mapLocalContentNews = new HashMap<>();
		}

		while (resultSet.next()) {
			int idNews = resultSet.getInt(1);
			if (!mapNews.containsKey(idNews)) {

				News news = new News();
				news.setImages(new ArrayList<>());
				news.setListLocalContentNews(new ArrayList<>());

				news.setId(idNews);
				news.setNewsDate(resultSet.getTimestamp(2));
				news.setStatus(resultSet.getBoolean(3));

				User user = new User();
				user.setId(resultSet.getInt(4));
				user.setRole(resultSet.getString(5));
				user.setName(resultSet.getString(6));
				user.setSurname(resultSet.getString(7));
				user.setEmail(resultSet.getString(8));
				user.setRegisterDate(resultSet.getDate(9));
				news.setAuthor(user);

				mapNews.put(idNews, news);
			}

			int idLocalContentNews = resultSet.getInt(10);
			if (!mapLocalContentNews.containsKey(idLocalContentNews)) {
				LocalContentNews localContentNews = new LocalContentNews();
				localContentNews.setId(idLocalContentNews);
				localContentNews.setIdNews(resultSet.getInt(11));
				localContentNews.setTitle(resultSet.getString(12));
				localContentNews.setLink(resultSet.getString(13));
				localContentNews.setLocal(resultSet.getString(14));

				mapLocalContentNews.put(idLocalContentNews, localContentNews);
			}

			int idImage = resultSet.getInt(15);
			if (idImage != 0) {
				Image image = new Image();
				image.setId(idImage);
				image.setLink(resultSet.getString(16));
				image.setStatus(resultSet.getBoolean(17));
				List<Image> images = mapNews.get(idNews).getImages();
				if (!images.contains(image)) {
					images.add(image);
				}
				
			}

		}

		final Map<Integer, News> map = mapNews;

		if (map != null) {
			mapLocalContentNews.values().forEach(v -> {
				map.get(v.getIdNews()).getListLocalContentNews().add(v);
			});
		}

		return map;
	}

}
