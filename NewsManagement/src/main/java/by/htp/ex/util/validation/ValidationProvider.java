package by.htp.ex.util.validation;

import by.htp.ex.util.validation.impl.ImageDataValidationImpl;
import by.htp.ex.util.validation.impl.UserDataValidationImpl;

public class ValidationProvider {
	private static final ValidationProvider instance = new ValidationProvider();
	
	private final UserDataValidation userDataValidation = new UserDataValidationImpl();
	private final ImageDataValidation imageDataValidation = new ImageDataValidationImpl();
	
	private ValidationProvider() {		
	}
	
	public UserDataValidation getUserDataValidation() {
		return userDataValidation;
	}
	
	public ImageDataValidation getImageDataValidation() {
		return imageDataValidation;
	}

	public static ValidationProvider getInstance() {
		return instance;
	}
	

}
