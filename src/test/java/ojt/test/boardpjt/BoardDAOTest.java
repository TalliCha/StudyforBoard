package ojt.test.boardpjt;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

//	@Inject
//	private BoardDAO dao;
//	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);

//	@Test
//	public void comm_create() throws Exception {
//		CommentVO vo = new CommentVO();
//
//		vo.setBno(1);
//		vo.setContent("안녕");
//		vo.setWriter("차차");
//
//		dao.comm_create(vo);
//
//	}

//	@Test
//	public void testViewContent() throws Exception {
//		
//		ConVO vo = new ConVO();
//		vo.setPno(1);
//		vo.setCategory("title");
//		
//		logger.info(dao.getList(vo).toString());
//	}
	//
	// @Test
	// public void comm_reply() throws Exception {
	// CommentVO vo = new CommentVO();
	//
	// vo.setBno(1);
	// vo.setContent("안녕");
	// vo.setWriter("차차");
	//
	// dao.comm_create(vo);
	//
	// }

	// @Test
	// public void testUpdate() throws Exception {
	// BoardVO vo = new BoardVO();
	// vo.setTitle("수정된 제목 입니다.");
	// vo.setContent("수정된 내용입니다.");
	// vo.setBno(2);
	// dao.update(vo);
	// }

	// @Test
	// public void testViewCount() throws Exception {
	// dao.viewCount(2);
	// }
	//
	// @Test
	// public void testViewContent() throws Exception {
	// logger.info(dao.viewContent(2).toString());
	// }

	// @Test
	// public void testClear() throws Exception {
	// dao.clear(2);
	// }

	// @Test
	// public void testDelete() throws Exception {
	// dao.delete(2);
	// }

}
