package ojt.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class BoardValidator implements Validator {

	private static final Integer MAX_TITLE = 80;
	private static final Integer MIN_TITLE = 3;
	
	private static final Integer MAX_CONTENT = 4100;
	private static final Integer MIN_CONTENT = 3;
	
	private static final Integer MAX_WRITER = 80;
	private static final Integer MIN_WRITER = 3;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {
		return BoardVO.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		BoardVO vo = (BoardVO) obj;

		StringBuffer msg = new StringBuffer();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "err_noTitle");

		if (vo.getTitle().getBytes().length > MAX_TITLE) {
			msg.append("err_maxTitle ,");
			errors.reject("title", "err_maxTitle");
		} else if (vo.getTitle().getBytes().length < MIN_TITLE) {
			msg.append("err_minTitle ,");
			errors.rejectValue("title", "err_minTitle");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "err_noContent");

		if (vo.getContent().getBytes().length > MAX_CONTENT) {
			msg.append("err_maxContent ,");
			errors.rejectValue("content", "err_maxContent");
		} else if (vo.getContent().getBytes().length < MIN_CONTENT) {
			msg.append("err_minContent ,");
			errors.rejectValue("content", "err_minContent");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "err_noWriter");

		if (vo.getWriter().getBytes().length > MAX_WRITER) {
			msg.append("err_maxWriter ,");
			errors.rejectValue("writer", "err_maxWriter");
		} else if (vo.getWriter().getBytes().length < MIN_WRITER) {
			msg.append("err_minWriter ,");
			errors.rejectValue("writer", "err_minWriter");
		}
		
		logger.info("boardValidation : {}", msg.toString());
	}

}
