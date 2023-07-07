package by.htp.ex.dao;

public class NewsDAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public NewsDAOException() {
		super();
	}

	public NewsDAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NewsDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public NewsDAOException(Throwable cause) {
		super(cause);
	}

}
