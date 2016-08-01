package ojt.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CommValidator  implements Validator {

	private static final Integer MAX_CONTENT = 500;
	private static final Integer MAX_WRITER = 80;
	
	private static final Logger logger = LoggerFactory.getLogger(ConValidator.class);
	
	@Override
	public boolean supports(Class<?> arg0) {
		return CommentVO.class.isAssignableFrom(arg0);
	}

	/**
	 * CommentVO 값 에러 체크 및 초기화
	 */		
	@Override
	public void validate(Object obj, Errors errors) {
		CommentVO vo = (CommentVO) obj;
		
		System.out.println(vo);
		
		StringBuffer msg = new StringBuffer();

		if (vo.getContent() == null || vo.getContent().equals("")) {
			msg.append("no_content ,");
			errors.rejectValue("content", "no_content");
		}
		
		if ( vo.getContent() == null && vo.getContent().getBytes().length > MAX_CONTENT) {
			msg.append("max_content ,");
			errors.rejectValue("content", "max_content");
		}
		
		if (vo.getWriter() == null || vo.getWriter().equals("")) {
			msg.append("no_writer ,");
			errors.rejectValue("writer", "no_writer");
		}
		if (vo.getWriter() == null && vo.getWriter().getBytes().length > MAX_WRITER) {
			msg.append("max_writer ,");
			errors.rejectValue("writer", "max_writer");
		}
		
		logger.info("CommValidation : {}", msg.toString() );

	}

}
