package ojt.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UploadValidator implements Validator {

	private static final Long MAX_FILESIZE = 10_000_000L;

	private static final Logger logger = LoggerFactory.getLogger(UploadValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {
		return UploadVO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UploadVO vo = (UploadVO) obj;

		StringBuffer msg = new StringBuffer();
		if (vo.getUploadFile().getSize() > MAX_FILESIZE) {
			msg.append("err_maxFileSize ,");
			errors.rejectValue("fsize", "err_maxFileSize");
		}

		logger.info("uploadValidation : {}", msg.toString());
	}

}
