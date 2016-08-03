package ojt.test.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ojt.test.domain.BoardVO;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.UploadVO;

public interface BoardService {
	public void create(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception;
	public void replyCreate(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception;
	public void update(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception;

	public void viewContent(BoardVO boVO) throws Exception;
	public void viewCount(BoardVO vo) throws Exception;

	public void delete(BoardVO vo) throws Exception;

	public List<BoardVO> getList(ConVO vo) throws Exception;
	public ConVO getMaxPage(ConVO vo) throws Exception;
	

	/*comm service*/
	public void comm_create(CommentVO commVO) throws Exception;
	public void comm_replyCreate(CommentVO commVO) throws Exception;
	public void comm_update(CommentVO commVO) throws Exception;

	public void comm_delete(CommentVO commVO) throws Exception;
	
	public List<CommentVO> comm_viewList(ConVO vo) throws Exception;
	public ConVO comm_getMaxPage(ConVO vo) throws Exception;

}
