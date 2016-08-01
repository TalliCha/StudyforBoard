package ojt.test.boardpjt;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ojt.test.domain.MemberVO;
import ojt.test.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class MemberDAOTest {

	@Inject
	private MemberDAO dao;

	@Test
	public void testTime() throws Exception {

		System.out.println(dao.getTime());

	}

	@Test
	public void testView() throws Exception {

		dao.viewMembers();

	}

	@Test
	public void testInsertMember() throws Exception {

		MemberVO vo = new MemberVO();
		vo.setUserid("user01");
		vo.setUserpw("user01");
		vo.setUsername("USER01");
		vo.setEmail("user01@aaa.com");

		dao.insertMember(vo);

	}

	@Test
	public void testReadMember() throws Exception {
		String userid = "user01";
		dao.readMember(userid);
	}

	@Test
	public void testReadWithPW() throws Exception {
		String userid = "user01";
		String userpw = "user01";
		dao.readWithPW(userid, userpw);
	}

}
