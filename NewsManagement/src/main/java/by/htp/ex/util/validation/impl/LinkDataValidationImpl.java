package by.htp.ex.util.validation.impl;

import by.htp.ex.util.name.LocalName;
import by.htp.ex.util.name.RegExName;
import by.htp.ex.util.validation.LinkDataValidation;
import by.htp.ex.util.validation.ValidationException;

public class LinkDataValidationImpl implements LinkDataValidation{
	@Override
	public void isLink(String link) throws ValidationException {
		if (!link.matches(RegExName.LINK)) {
			throw new ValidationException(LocalName.LINK_VALIDATION);
		}
	}

}
