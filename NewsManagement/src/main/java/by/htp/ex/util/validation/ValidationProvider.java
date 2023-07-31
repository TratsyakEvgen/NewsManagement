package by.htp.ex.util.validation;

import by.htp.ex.util.validation.impl.LinkDataValidationImpl;
import by.htp.ex.util.validation.impl.UserDataValidationImpl;

public class ValidationProvider {
	private static final ValidationProvider instance = new ValidationProvider();
	
	private final UserDataValidation userDataValidation = new UserDataValidationImpl();
	private final LinkDataValidation linkDataValidation = new LinkDataValidationImpl();
	
	private ValidationProvider() {		
	}
	
	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}
	
	public LinkDataValidation getLinkDataValidation() {
		return linkDataValidation;
	}

	public static ValidationProvider getInstance() {
		return instance;
	}
	

}
