package ojt.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

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
//		
//		for (MultipartFile file : vo.getUploadFiles() ) {
//			if (file.getSize() > MAX_FILESIZE ){
//				msg.append("err_maxFileSize ,");
//				errors.rejectValue("uploadFiles", "err_maxFileSize");
//			}
//		}

		logger.info("uploadValidation : {}", msg.toString());
	}

}
