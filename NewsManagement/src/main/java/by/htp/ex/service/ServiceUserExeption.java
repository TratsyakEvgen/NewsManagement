package by.htp.ex.service;

public class ServiceUserExeption extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceUserExeption() {
		super();
		
	}

	public ServiceUserExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ServiceUserExeption(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceUserExeption(String message) {
		super(message);
		
	}

	public ServiceUserExeption(Throwable cause) {
		super(cause);
		
	}

}
