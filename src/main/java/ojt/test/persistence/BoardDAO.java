package ojt.test.persistence;

import java.util.List;

import ojt.test.domain.BoardVO;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.UploadVO;

public interface BoardDAO {
	public void create(BoardVO vo) throws Exception;

	public void replyCreate(BoardVO vo) throws Exception;

	public void update(BoardVO vo) throws Exception;

	public void viewContent(BoardVO vo) throws Exception;

	public void viewCount(BoardVO vo) throws Exception;

	public void clear(Integer bno) throws Exception;

	public boolean deleteCheck(BoardVO vo) throws Exception;
	
	public void delete( BoardVO vo) throws Exception;
	public void deleteChildLink(BoardVO boVO) throws Exception;

	public List<BoardVO> getList(ConVO vo) throws Exception;

	public ConVO getMaxPage(ConVO vo) throws Exception;

	/* Upload 처리 */
	public void upload(UploadVO upVO) throws Exception;

	public void viewUpload_file(BoardVO boVO, UploadVO upVO) throws Exception;

	public void uploadCount(BoardVO boVO) throws Exception;

	public void uploadModify(UploadVO upVO) throws Exception;

	public boolean uploadDelete(boolean delete, UploadVO upVO) throws Exception;

	
	/* Comment 처리 */
	public void comm_create( CommentVO commVO ) throws Exception;
	public void comm_replyCreate( CommentVO commVO ) throws Exception;
	
	public void comm_update( CommentVO commVO ) throws Exception;
	
	public void comm_delete( CommentVO commVO) throws Exception;
	public void comm_deleteChildLink(CommentVO commVO) throws Exception;

	public List<CommentVO> comm_viewList( ConVO conVO ) throws Exception;
	
	public ConVO comm_getMaxPage(ConVO vo) throws Exception;
	


	
}
