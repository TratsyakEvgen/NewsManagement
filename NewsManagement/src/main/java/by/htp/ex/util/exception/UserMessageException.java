package by.htp.ex.util.exception;

public class UserMessageException extends Exception {
	private String userMessage;
	private boolean systemError;

	private static final long serialVersionUID = 1L;

	public UserMessageException() {
		super();
	}

	public UserMessageException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserMessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserMessageException(String message) {
		super(message);
	}

	public UserMessageException(Throwable cause) {
		super(cause);
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public boolean isSystemError() {
		return systemError;
	}

	public void setSystemError(boolean systemError) {
		this.systemError = systemError;
	}
	
	public void setAll(UserMessageException e) {
		userMessage = e.userMessage;
		this.systemError = e.systemError;
	}

}
