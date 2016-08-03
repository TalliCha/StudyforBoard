package ojt.test.domain;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class MultipartValidator implements Validator {
	private static final Long MAX_FILESIZE = 10_000_000L;

	private static final Logger logger = LoggerFactory.getLogger(MultipartValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {
		return MultipartValidator.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		logger.info("접속 했으요");
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) obj;

		List<MultipartFile> file_list = multipartRequest.getFiles("uploadFile");
		
		 
		StringBuffer msg = new StringBuffer();
		
		if (!file_list.isEmpty()) {
			for (MultipartFile file : file_list) {
				if (file.getSize() > MAX_FILESIZE ){
					msg.append("err_maxFileSize ,");
					errors.rejectValue("uploadFiles", "err_maxFileSize");
				}
			}
		}

		logger.info("uploadValidation : {}", msg.toString());
	}
}
