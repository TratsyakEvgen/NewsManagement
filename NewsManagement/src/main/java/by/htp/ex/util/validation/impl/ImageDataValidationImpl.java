package by.htp.ex.util.validation.impl;

import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.RegExName;
import by.htp.ex.util.validation.ImageDataValidation;
import by.htp.ex.util.validation.ValidationException;

public class ImageDataValidationImpl implements ImageDataValidation{
	@Override
	public void isLink(String link) throws ValidationException {
		if (!link.matches(RegExName.LINK)) {
			throw new ValidationException(LocalName.LINK_VALIDATION);
		}
	}

}
