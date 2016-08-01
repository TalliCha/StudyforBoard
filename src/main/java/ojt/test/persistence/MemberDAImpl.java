package ojt.test.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import ojt.test.domain.MemberVO;

@Repository
public class MemberDAImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "ojt.test.mapper.MemberMapper";

	@Override
	public List<MemberVO> viewMembers() {
		
		return sqlSession.selectList(namespace+".viewMembers");
	}
	
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace + ".insertMember", vo);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {

		return (MemberVO) sqlSession.selectOne(namespace + ".selectMember", userid);
	}

	@Override
	public MemberVO readWithPW(String userid, String userpw) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);

		return sqlSession.selectOne(namespace + ".readWithPW", paramMap);
	}

	

}
