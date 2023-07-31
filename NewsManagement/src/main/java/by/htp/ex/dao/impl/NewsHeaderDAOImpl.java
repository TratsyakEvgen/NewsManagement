package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import by.htp.ex.bean.NewsHeader;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.NewsHeaderDAO;
import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;

public class NewsHeaderDAOImpl implements NewsHeaderDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static final String QUERY_INSERT_NEWS = "INSERT INTO news (news.news_date, news.status, news.users_id) VALUE (?,?,?)";

	@Override
	public int create(NewsHeader newsHeader) throws DaoException {
		int id = 0;

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_INSERT_NEWS,
						Statement.RETURN_GENERATED_KEYS)) {

			statment.setTimestamp(1, new Timestamp(newsHeader.getNewsDate().getTime()));
			statment.setBoolean(2, newsHeader.isStatus());
			statment.setInt(3, newsHeader.getIdUser());
			statment.executeUpdate();
			try (ResultSet resultSet = statment.getGeneratedKeys()) {
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
			}

		} catch (SQLException e) {
			throw new DaoException("SQLException create news header.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error create news header.", e);
		}

		return id;
	}

	private static final String QUERY_UPDATE_USER = "UPDATE news SET news.users_id=? WHERE news.id =?";

	@Override
	public void updateUser(int idNews, int idUser) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_UPDATE_USER)) {

			statment.setInt(1, idUser);
			statment.setInt(2, idNews);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQLException update user in news.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error update user in news.", e);
		}

	}

	private static final String QUERY_UPDATE_STATUS = "UPDATE news SET news.status=? WHERE news.id =?";

	@Override
	public void updateStatus(int idNews, boolean status) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_UPDATE_STATUS)) {

			statment.setBoolean(1, status);
			statment.setInt(2, idNews);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQLException update status news.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error update status news.", e);
		}

	}

	private static final String QUERY_ADD_IMAGE = "INSERT INTO images_in_news VALUE (?,?)";

	@Override
	public void addImage(int idNews, int idImage) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_ADD_IMAGE)) {

			statment.setInt(1, idImage);
			statment.setInt(2, idNews);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQLException add image in news.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error add image in news.", e);
		}

	}

	private static final String QUERY_DELETE_IMAGE_IN_NEWS = "DELETE FROM images_in_news WHERE images_in_news.images_id = ?"
			+ " AND images_in_news.news_id = ?";

	@Override
	public void deleteImage(int idNews, int idImage) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_DELETE_IMAGE_IN_NEWS)) {

			statment.setInt(1, idImage);
			statment.setInt(2, idNews);
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQLException delete image in news.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error delete image in news.", e);
		}

	}

	private static final String QUERY_SELECT_BY_ID_IMAGE_AND_NEWS = "SELECT * FROM images_in_news WHERE images_in_news.images_id = ?"
			+ " AND images_in_news.news_id = ?";

	@Override
	public boolean isExistImageInNews(int idNews, int idImage) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_SELECT_BY_ID_IMAGE_AND_NEWS)) {

			statment.setInt(1, idImage);
			statment.setInt(2, idNews);
			try (ResultSet resultSet = statment.executeQuery()) {
				return resultSet.next();
			}

		} catch (SQLException e) {
			throw new DaoException("SQLException add image in news.", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error add image in news.", e);
		}

	}

}
