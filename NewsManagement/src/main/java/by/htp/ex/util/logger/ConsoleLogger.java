package by.htp.ex.util.logger;

public class ConsoleLogger implements Logger {

	private final static ConsoleLogger instance = new ConsoleLogger();

	private ConsoleLogger() {
	}

	@Override
	public void warn(Exception e) {
		e.printStackTrace();
	}
	
	public static ConsoleLogger getInstance() {
		return instance;
	}

}
