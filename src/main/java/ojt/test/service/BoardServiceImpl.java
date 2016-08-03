package ojt.test.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public void create(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception {

		dao.create(boVO);

		for (MultipartFile file : file_list) {

			if (!file.getOriginalFilename().equals("")) {
				upload(boVO, upVO, file); // 파일 처리
				dao.upload(upVO); // DB에 파일 기록 수정
			}
		}
		dao.uploadCount(boVO); // 파일 갯수 갱신

	}

	@Override
	public void replyCreate(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception {
		dao.replyCreate(boVO);

		for (MultipartFile file : file_list) {

			if (!file.getOriginalFilename().equals("")) {
				upload(boVO, upVO, file); // 파일 처리
				dao.upload(upVO); // DB에 파일 기록 수정
			}
		}
		dao.uploadCount(boVO); // 파일 갯수 갱신

	}

	@Override
	public void update(BoardVO boVO, UploadVO upVO, List<MultipartFile> file_list) throws Exception {
		dao.update(boVO);

		System.out.println("########" + boVO.getDeleteFiles());
		// 체크된 파일 지우기
		if (boVO.getDeleteFiles() != null) {

			for (String delete_upload_fname : boVO.getDeleteFiles()) {
				File upload_file = new File(UPLOAD_PATH + "/" + delete_upload_fname);
				if (upload_file.exists()) {
					upload_file.delete();
				}
				dao.deleteFile(delete_upload_fname);
			}
		}

		for (MultipartFile file : file_list) {

			if (!file.getOriginalFilename().equals("")) {
				upload(boVO, upVO, file); // 파일 처리
				dao.upload(upVO); // DB에 파일 기록 수정
			}
		}
		dao.uploadCount(boVO); // 파일 갯수 갱신
	}

	private void upload(BoardVO boVO, UploadVO upVO, MultipartFile file) throws Exception {

		// String path =
		// request.getSession().getServletContext().getRealPath("resources/"+boVO.getFname()
		// );

		File dir = new File(UPLOAD_PATH);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}

		uploadVoSetting(boVO, upVO, file); // upVO 세팅

		System.out.println(upVO.toString());

		// 파일저장
		file.transferTo(new File(UPLOAD_PATH + "/" + upVO.getUpload_fname()));

	}

	private void uploadVoSetting(BoardVO boVO, UploadVO upVO, MultipartFile file) {
		upVO.setBno(boVO.getBno());
		upVO.setOriginal_fname(file.getOriginalFilename());
		upVO.setUpload_fname(UUID.randomUUID().toString() + "_" + file.getOriginalFilename());
		upVO.setFsize(file.getSize());
		upVO.setFtype(file.getContentType());
	}

	private void deleteFile(BoardVO boVO) throws Exception {

		boVO.setUploadVOs(dao.viewUpload_file(boVO)); // 파일 리스트 저장
		if (boVO.getUploadVOs() != null) { // 파일이 존재하면 삭제하기
			for (UploadVO file : boVO.getUploadVOs()) {
				File upload_file = new File(UPLOAD_PATH + "/" + file.getUpload_fname());
				if (upload_file.exists()) {
					upload_file.delete();
				}
			}
		}
	}

	@Override
	public void viewContent(BoardVO boVO) throws Exception {
		dao.viewContent(boVO);
		boVO.setUploadVOs(dao.viewUpload_file(boVO)); // 파일 리스트 저장
	}

	@Override
	public void viewCount(BoardVO vo) throws Exception {
		dao.viewCount(vo);
	}

	@Override
	public void delete(BoardVO boVO) throws Exception {

		deleteFile(boVO); // 1. 로컬에 저장된 파일 지우기 -- 순서 중요.
		dao.delete(boVO); // 2. 파일 지우기
		dao.deleteChildLink(boVO); // 3. 자식들의 부모 링크 삭제
		dao.uploadCount(boVO); // 4. 파일 갯수 갱신

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
		dao.comm_delete(commVO); // 댓글 지우기
		dao.comm_deleteChildLink(commVO); // 자식들의 부모 링크 삭제

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
