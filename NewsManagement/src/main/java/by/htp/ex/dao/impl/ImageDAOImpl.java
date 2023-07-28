package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.Image;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.ImageDAO;
import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;
import by.htp.ex.util.name.ParamName;

public class ImageDAOImpl implements ImageDAO {
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	public ImageDAOImpl() {
	}

	private static final String QUERY_GET_ALL_IMAGES = "SELECT * FROM images";

	@Override
	public List<Image> getAll() throws DaoException {
		List<Image> images = null;
		try (Connection connection = connectionPool.takeConnection();
				Statement statment = connection.createStatement()) {
			try (ResultSet resultSet = statment.executeQuery(QUERY_GET_ALL_IMAGES)) {
				if (resultSet.isBeforeFirst()) {
					images = new ArrayList<>();
				}
				while (resultSet.next()) {
					Image image = new Image();
					image.setId(resultSet.getInt(ParamName.ID));
					image.setLink(resultSet.getString(ParamName.LINK));
					image.setStatus(resultSet.getBoolean(ParamName.STATUS));
					images.add(image);
				}
			}

		} catch (SQLException e) {
			throw new DaoException("SQL Exception get all images", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error get all images.", e);
		}
		return images;
	}

	private static final String QUERY_UPDATE_IMAGE = "UPDATE images SET images.link = ?, images.status = ? WHERE images.id =?";

	@Override
	public void update(Image image) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_UPDATE_IMAGE)) {

			statment.setString(1, image.getLink());
			statment.setBoolean(2, image.isStatus());
			statment.setInt(3, image.getId());
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQL Exception update images", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error update images.", e);
		}
	}

	private static final String QUERY_CREATE_IMAGE = "INSERT INTO images (images.link, images.status) VALUE (?,?)";

	@Override
	public void create(Image image) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_CREATE_IMAGE)) {

			statment.setString(1, image.getLink());
			statment.setBoolean(2, image.isStatus());
			statment.executeUpdate();

		} catch (SQLException e) {
			throw new DaoException("SQL Exception create image", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error create image.", e);
		}
	}

	private static final String QUERY_FIND_BY_LINK = "SELECT images.id FROM images WHERE images. link =  ?";

	@Override
	public int findByLink(String link) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
				PreparedStatement statment = connection.prepareStatement(QUERY_FIND_BY_LINK)) {
			statment.setString(1, link);
			try (ResultSet resultSet = statment.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(ParamName.ID);
				}
				return 0;
			}
		} catch (SQLException e) {
			throw new DaoException("SQL Exception check is exist link", e);
		} catch (ConnectionPoolException e) {
			throw new DaoException("Сonnection setup error when check is exist link.", e);
		}

	}

}
