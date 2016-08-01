package ojt.test.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ConValidator implements Validator {

	private static final int DEFAULT_PNO = 1;
	private static final int DEFAULT_PAGESIZE = 10;
	private static final int DEFAULT_NAVISIZE = 10;
	
	private static final int DEFAULT_COMM_PNO = 1;
	private static final int DEFAULT_COMM_PAGESIZE = 10;
	private static final int DEFAULT_COMM_NAVISIZE = 10;
	
	private static final String DEFAULT_KEYWORD = ".";

	private static final Logger logger = LoggerFactory.getLogger(ConValidator.class);

	@Override
	public boolean supports(Class<?> arg0) {
		return ConVO.class.isAssignableFrom(arg0);
	}

	/**
	 * ListCon 값 에러 체크 및 초기화
	 */
	@Override
	public void validate(Object obj, Errors errors) {
		ConVO vo = (ConVO) obj;
		
		StringBuffer msg = new StringBuffer();

		
		/*list*/
		if (vo.getPno() == 0) {
			msg.append("no_pno ,");
			errors.rejectValue("pno", "no_pno");
			vo.setPno(DEFAULT_PNO);
		}

		if (vo.getPageSize() == 0) {
			msg.append("no_pageSize ,");
			errors.rejectValue("pageSize", "no_pageSize");
			vo.setPageSize(DEFAULT_PAGESIZE);
		}


		if (vo.getNaviSize() == 0) {
			msg.append("no_naviSize ,");
			errors.rejectValue("naviSize", "no_naviSize");
			vo.setNaviSize(DEFAULT_NAVISIZE);
		}
		
		
		/*comm_list*/
		if (vo.getComm_pno() == 0) {
			msg.append("no_comm_pno ,");
			errors.rejectValue("comm_pno", "no_comm_pno");
			vo.setComm_pno(DEFAULT_COMM_PNO);
		}

		if (vo.getComm_pageSize() == 0) {
			msg.append("no_comm_pageSize ,");
			errors.rejectValue("comm_pageSize", "no_comm_pageSize");
			vo.setComm_pageSize(DEFAULT_COMM_PAGESIZE);
		}


		if (vo.getComm_naviSize() == 0) {
			msg.append("no_comm_naviSize ,");
			errors.rejectValue("comm_naviSize", "no_comm_naviSize");
			vo.setComm_naviSize(DEFAULT_COMM_NAVISIZE);
		}
		
		
		/* keyWord */
		if (vo.getKeyWord() == null || vo.getKeyWord().equals("")) {
			msg.append("no_keyWord ,");
			errors.rejectValue("pageSize", "no_keyWord");
			vo.setKeyWord(DEFAULT_KEYWORD);
		}
		
		logger.info("ConValidation : {}",msg.toString());

	}

}
