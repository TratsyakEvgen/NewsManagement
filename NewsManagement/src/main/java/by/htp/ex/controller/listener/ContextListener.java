package by.htp.ex.controller.listener;

import by.htp.ex.dao.connection.pool.ConnectionPool;
import by.htp.ex.dao.connection.pool.ConnectionPoolException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{
	
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		try {
			connectionPool.dispose();
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
		}
	}

}
