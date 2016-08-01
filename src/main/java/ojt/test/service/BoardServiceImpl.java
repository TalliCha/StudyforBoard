package ojt.test.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ojt.test.domain.BoardVO;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.UploadVO;
import ojt.test.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	
	private static final String UPLOAD_PATH = "c:/upload";

	@Inject
	private BoardDAO dao;

	@Override
	public void create(BoardVO boVO, UploadVO upVO) throws Exception {

		dao.create(boVO);

		if (!upVO.getUploadFile().isEmpty()) { // 첨부 파일이 존재 할때
			upload(boVO, upVO); // 파일 처리
			dao.upload(upVO); // DB에 파일 기록 수정
			dao.uploadCount(boVO); // 파일 갯수 갱신
		}

	}

	@Override
	public void replyCreate(BoardVO boVO, UploadVO upVO) throws Exception {
		dao.replyCreate(boVO);

		if (!upVO.getUploadFile().isEmpty()) { // 첨부 파일이 존재 할때
			upload(boVO, upVO); // 파일 처리
			dao.upload(upVO); // DB에 파일 기록 수정
			dao.uploadCount(boVO); // 파일 갯수 갱신
		}
	}

	@Override
	public void update(BoardVO boVO, UploadVO upVO) throws Exception {
		dao.update(boVO);

		if (!upVO.getUploadFile().isEmpty() ) { // 첨부 파일이 존재 할때
			upload(boVO, upVO); // 파일 처리
			
			if (upVO.getFno()==0) {  
				dao.upload(upVO); // 이전 파일이 존재 안할때 DB에 파일 기록 추가
			}else{
				dao.uploadModify(upVO); // 파일이 존재 할때 ,DB에 파일 기록 수정
			}
			
			dao.uploadCount(boVO); // 파일 갯수 갱신
		}
	}

	private void upload(BoardVO boVO, UploadVO upVO) throws Exception {

		// String path =
		// request.getSession().getServletContext().getRealPath("resources/"+boVO.getFname()
		// );

		File dir = new File(UPLOAD_PATH);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		deleteFile(upVO);  	// 이전 파일 삭제

		uploadVoSetting(boVO, upVO); // upVO 세팅

		// 파일저장
		upVO.getUploadFile().transferTo(new File(UPLOAD_PATH + "/" + upVO.getUpload_fname()));

	}

	private void uploadVoSetting(BoardVO boVO, UploadVO upVO) {
		upVO.setBno(boVO.getBno());
		upVO.setFtype(upVO.getUploadFile().getContentType());
		upVO.setOriginal_fname(upVO.getUploadFile().getOriginalFilename());
		upVO.setUpload_fname(UUID.randomUUID().toString() + "_" + upVO.getUploadFile().getOriginalFilename());
		upVO.setFsize(upVO.getUploadFile().getSize());
	}
	
	private void deleteFile( UploadVO upVO) {
		if(upVO.getFno() != 0){
			File upload_file = 	new File(UPLOAD_PATH + "/" + upVO.getUpload_fname());
			if(upload_file.exists()){
				upload_file.delete();
			}
		}
	}

	@Override
	public void viewContent(BoardVO boVO, UploadVO upVO) throws Exception {
		dao.viewContent(boVO);
		if (boVO.getUpload_file() > 0) {
			dao.viewUpload_file(boVO, upVO);
		}
	}

	@Override
	public void viewCount(BoardVO vo) throws Exception {
		dao.viewCount(vo);
	}

	@Override
	public void delete(BoardVO boVO, UploadVO upVO) throws Exception {
		
		dao.delete( boVO );  // 파일 지우기
		dao.deleteChildLink( boVO );  // 자식들의 부모 링크 삭제
		deleteFile( upVO );
		dao.uploadCount(boVO); // 파일 갯수 갱신
		
	}

	@Override
	public List<BoardVO> getList(ConVO vo) throws Exception {
		
		return dao.getList(vo);
	}

	@Override
	public ConVO getMaxPage(ConVO vo) throws Exception {
		return dao.getMaxPage(vo);
	}

	@Override
	public void comm_create(CommentVO commVO) throws Exception {
		dao.comm_create(commVO);
	}

	@Override
	public void comm_replyCreate(CommentVO commVO) throws Exception {
		dao.comm_replyCreate(commVO);
		
	}

	@Override
	public void comm_update(CommentVO commVO) throws Exception {
		dao.comm_update(commVO);
		
	}

	@Override
	public void comm_delete(CommentVO commVO) throws Exception {
		dao.comm_delete(commVO);  // 댓글 지우기
		dao.comm_deleteChildLink(commVO);  // 자식들의 부모 링크 삭제
		
	}

	@Override
	public List<CommentVO> comm_viewList(ConVO vo) throws Exception {
		return dao.comm_viewList(vo);
	}

	@Override
	public ConVO comm_getMaxPage(ConVO vo) throws Exception {
		return dao.comm_getMaxPage(vo);
	}

}
