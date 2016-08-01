package ojt.test.boardpjt;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ojt.test.domain.ConVO;
import ojt.test.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardServiceTest {

	@Inject
	private BoardService service;
	private static Logger logger = LoggerFactory.getLogger(BoardServiceTest.class);

//	@Test
//	public void testCreate() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("제목 입니다.");
//		vo.setContent("내용입니다.");
//		vo.setWriter("스프링 작성자");
//		service.create(vo);
//	}
//
//	@Test
//	public void testUpdate() throws Exception {
//		BoardVO vo = new BoardVO();
//		vo.setTitle("수정된 제목 입니다.");
//		vo.setContent("수정된 내용입니다.");
//		vo.setBno(2);
//		service.update(vo);
//	}

//	@Test
//	public void testViewCount() throws Exception {
//		service.viewCount(2);
//	}

//	@Test
//	public void testViewContent() throws Exception {
//		logger.info(service.viewContent(3).toString());
//	}

//	@Test
//	public void testClear() throws Exception {
//		service.clear(2);
//	}

//	@Test
//	public void testDelete() throws Exception {
//		service.delete(2);
//	}

	@Test
	public void testMaxPage() throws Exception {
		ConVO vo = new ConVO();
		vo.setKeyWord(".");
		logger.info(service.getMaxPage(vo).toString());
	}
	
	@Test
	public void testGetList() throws Exception {
		ConVO vo = new ConVO();
		vo.setPno(1);
		vo.setPageSize(3);
		vo.setKeyWord(".");
		logger.info(service.getList(vo).toString());
	}

}
