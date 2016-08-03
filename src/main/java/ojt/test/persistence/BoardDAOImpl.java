package ojt.test.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import ojt.test.domain.BoardVO;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.UploadVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;

	private static String NAMESPACE = "ojt.test.mapper.BoardMapper";

	@Override
	public void create(BoardVO vo) throws Exception {
		vo.setBno(sqlSession.selectOne(NAMESPACE + ".next_bno"));
		sqlSession.insert(NAMESPACE + ".create", vo);
	}

	@Override
	public void replyCreate(BoardVO vo) throws Exception {
		vo.setParent_bno(vo.getBno());
		vo.setBno(sqlSession.selectOne(NAMESPACE + ".next_bno"));
		sqlSession.update(NAMESPACE + ".reply_step1_2", vo);
		sqlSession.insert(NAMESPACE + ".reply_step2_2", vo);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public void viewContent(BoardVO vo) throws Exception {
		BoardVO dbVO = sqlSession.selectOne(NAMESPACE + ".viewContent", vo);

		BeanUtils.copyProperties(dbVO, vo); // DB 결과 값 저장
	}

	@Override
	public void viewCount(BoardVO vo) throws Exception {
		sqlSession.update(NAMESPACE + ".viewCount", vo);
	}

	@Override
	public void clear(Integer bno) throws Exception {
		sqlSession.update(NAMESPACE + ".clear", bno);

	}

	@Override
	public boolean deleteCheck(BoardVO vo) throws Exception {

		String check = sqlSession.selectOne(NAMESPACE + ".deleteCheck", vo);
		return (check == null || check.equals("success")) ? true : false;
	}

	@Override
	public void delete(BoardVO vo) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete", vo);
	}
	
	@Override
	public void deleteChildLink(BoardVO boVO) {
		sqlSession.delete(NAMESPACE + ".deleteChildLink", boVO);
	}

	@Override
	public List<BoardVO> getList(ConVO vo) throws Exception {

		return sqlSession.selectList(NAMESPACE + ".viewList", vo);
	}

	@Override
	public ConVO getMaxPage(ConVO vo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".countList", vo);
	}

	@Override
	public void upload(UploadVO upVO) throws Exception {
		sqlSession.insert(NAMESPACE + ".upload", upVO);
	}

	@Override
	public void uploadModify(UploadVO upVO) {
		sqlSession.update(NAMESPACE + ".uploadModify", upVO);
	}

	@Override
	public List<UploadVO> viewUpload_file(BoardVO boVO) {
		return sqlSession.selectList(NAMESPACE + ".viewUpload", boVO);
	}

	@Override
	public void uploadCount(BoardVO boVO) {
		sqlSession.update(NAMESPACE + ".uploadCount", boVO);
	}

	@Override
	public boolean uploadDelete(boolean delete, UploadVO upVO) {
		if (delete) {
			sqlSession.delete(NAMESPACE + ".uploadDelete", upVO);
		}
		return delete;
	}

	@Override
	public void comm_create(CommentVO commVO) throws Exception {
		commVO.setCno(sqlSession.selectOne(NAMESPACE + ".next_cno"));
		sqlSession.insert(NAMESPACE + ".comm_create", commVO);

	}

	@Override
	public void comm_replyCreate(CommentVO commVO) throws Exception {
		commVO.setParent_cno(commVO.getCno());
		commVO.setCno(sqlSession.selectOne(NAMESPACE + ".next_cno"));
		sqlSession.update(NAMESPACE + ".comm_reply_step1_2", commVO);
		sqlSession.insert(NAMESPACE + ".comm_reply_step2_2", commVO);

	}

	@Override
	public void comm_update(CommentVO commVO) throws Exception {
		sqlSession.update(NAMESPACE + ".comm_update", commVO);
	}

	@Override
	public void comm_delete(CommentVO commVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".comm_delete", commVO);

	}

	@Override
	public void comm_deleteChildLink(CommentVO commVO) throws Exception {
		sqlSession.delete(NAMESPACE + ".comm_deleteChildLink", commVO);

	}

	@Override
	public List<CommentVO> comm_viewList(ConVO conVO) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".comm_viewList", conVO);
	}

	@Override
	public ConVO comm_getMaxPage(ConVO vo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".comm_countList",vo);
	}

	@Override
	public void deleteFile(String delete_upload_fname) {
		sqlSession.delete(NAMESPACE + ".deleteFile", delete_upload_fname);
	}

}
