package by.htp.ex.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.impl.GoToAuthentication;
import by.htp.ex.controller.impl.DoSIgnIn;
import by.htp.ex.controller.impl.DoSignOut;
import by.htp.ex.controller.impl.GoToBasePage;
import by.htp.ex.controller.impl.GoToErrorPage;
import by.htp.ex.controller.impl.GoToRegistration;
import by.htp.ex.controller.impl.GoToViewNews;
import by.htp.ex.controller.impl.ChangeLocal;
import by.htp.ex.controller.impl.DoRegistration;

public final class CommandProvider {
	private final Map<CommandName, Command> commands = new HashMap<>();
	
	private final static CommandProvider instance = new CommandProvider(); 
	
	private CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION, new GoToRegistration());
		commands.put(CommandName.DO_REGISTRATION, new DoRegistration());
		commands.put(CommandName.DO_SIGN_IN, new DoSIgnIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.CHANGE_LOCAL, new ChangeLocal());
		commands.put(CommandName.GO_TO_AUTHENTICATION, new GoToAuthentication());
		commands.put(CommandName.GO_TO_ERROR_PAGE, new GoToErrorPage());
	}
	
	public final static CommandProvider getInstance() {
		return instance;
	}
	
	
	public final Command getCommand(String name) {
		CommandName commandName;
		try {
			commandName = CommandName.valueOf(name.toUpperCase());
		} catch (IllegalArgumentException | NullPointerException e) {
			commandName = CommandName.GO_TO_ERROR_PAGE;
		}		
		Command command = commands.get(commandName);
		return command;
	}

}
