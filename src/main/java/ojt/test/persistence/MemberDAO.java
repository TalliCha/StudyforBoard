package ojt.test.persistence;

import java.util.List;

import ojt.test.domain.MemberVO;

public interface MemberDAO {
	public String getTime();

	public List<MemberVO> viewMembers();
	
	public void insertMember(MemberVO vo);

	public MemberVO readMember(String userid) throws Exception;

	public MemberVO readWithPW(String userid, String userpw) throws Exception;

}
