package ojt.test.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ojt.test.domain.BoardVO;
import ojt.test.domain.BoardValidator;
import ojt.test.domain.CommValidator;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.ConValidator;
import ojt.test.domain.UploadVO;
import ojt.test.domain.UploadValidator;
import ojt.test.service.BoardService;

@Controller
public class BoardController {

	@Inject
	BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String viewListGET(@Valid ConVO conVO, BindingResult conResult) {
		logger.info("[BOARD_GET: list page.]");

		if (conResult.hasErrors()) {
			logger.info("set init :ConVO : {}. {}", conVO);
		} else {

			logger.info("get :ConVO : {}. {}", conVO);
		}
		return "/board/list";
	}

	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public String viewListPOST(@Valid ConVO conVO, BindingResult conResult) {
		logger.info("[BOARD_POST: list page.]");

		if (conResult.hasErrors()) {
			logger.info("set init :ConVO : {}. {}", conVO);
		} else {

			logger.info("get :ConVO : {}. {}", conVO);
		}
		return "/board/list";
	}

	@RequestMapping(value = "/board/getList", method = RequestMethod.POST)
	public @ResponseBody List<BoardVO> getList(ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[GETLIST_POST: boardList.]");
		logger.info("ConVO : {}", conVO);

		List<BoardVO> getList = service.getList(conVO);

		logger.info("getlist : {}", getList);

		return getList;
	}

	@RequestMapping(value = "/board/getMaxPage", method = RequestMethod.POST)
	public @ResponseBody ConVO getMaxPage(ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[GETMAXCOUNT_POST: getMaxCount.]");
		logger.info("ConVO : {}", conVO);

		return service.getMaxPage(conVO);
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(ConVO conVO) {
		logger.info("[WRITE_POST: content page.]");
		logger.info("ConVO : {}", conVO);

		return "/board/write";
	}

	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public String create(HttpServletRequest request, @Valid BoardVO boVO, BindingResult boResult, ConVO conVO,
			@Valid UploadVO upVO, BindingResult upResult, Model model, RedirectAttributes redirectAttr)
			throws Exception {
		logger.info("[CREATE_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/write";
		} else if (upResult.hasErrors()) {
			model.addAttribute("sendMsg", "fileErr");
			return "/board/write";
		} else {
			service.create(boVO, upVO);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}
	}

	@RequestMapping(value = "/board/reply", method = RequestMethod.POST)
	public String reply(BoardVO boVO, ConVO conVO) throws Exception {
		logger.info("[MODIFY_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		return "/board/reply";
	}

	@RequestMapping(value = "/board/replycreate", method = RequestMethod.POST)
	public String replyCreate( @Valid BoardVO boVO, BindingResult boResult, ConVO conVO, UploadVO upVO, Model model,
			RedirectAttributes redirectAttr) throws Exception {
		logger.info("[REPLYCREATE_POST: content page ]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/reply";

		} else {
			service.replyCreate(boVO, upVO);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}

	}

	@RequestMapping(value = "/board/page", method = RequestMethod.GET)
	public String pageGET(BoardVO boVO, UploadVO upVO, @Valid ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[PAGE_GET: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		service.viewCount(boVO); // 글자 조회 1추가
		service.viewContent(boVO, upVO);
		return "/board/page";
	}

	@RequestMapping(value = "/board/page", method = RequestMethod.POST)
	public String pagePOST(UploadVO upVO, ConVO conVO, BoardVO boVO) throws Exception {
		logger.info("[PAGE_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		service.viewCount(boVO); // 글자 조회 1추가
		service.viewContent(boVO, upVO);

		return "/board/page";
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(BoardVO boVO, UploadVO upVO, ConVO conVO, RedirectAttributes redirectAttr) throws Exception {
		logger.info("[DELETE_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		service.delete(boVO, upVO);

		logger.info("delete: action.{}", "삭제 함");
		redirectAttr.addFlashAttribute("sendMsg", "delSucc");
		redirectAttr.addFlashAttribute("conVO", conVO);
		return "redirect:/board";
	}

	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public String modify(BoardVO boVO, ConVO conVO, UploadVO upVO) throws Exception {
		logger.info("[MODIFY_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);
		return "/board/modify";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(@Valid BoardVO boVO, BindingResult boResult, ConVO conVO, UploadVO upVO, Model model,
			RedirectAttributes redirectAttr) throws Exception {
		logger.info("[UPDATE_POST: content page.]");
		logger.info("BoardVO : {}", boVO);
		logger.info("ConVO : {}", conVO);
		logger.info("UploadVO : {}", upVO);

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/modify";
		} else {
			service.update(boVO, upVO);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}
	}
	
	/* Comment */

	@RequestMapping(value = "/board/comm_create", method = RequestMethod.POST)
	public @ResponseBody boolean comm_create(@Valid CommentVO commVO, BindingResult commResult )
			throws Exception {
		logger.info("[COMM_CREATE_POST: content page.]");
		logger.info("CommentVO : {}", commVO);

		if (commResult.hasErrors()) { // commVO 에 문제가 없을 때
			return false;
		} else {
			service.comm_create(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_replycreate", method = RequestMethod.POST)
	public @ResponseBody boolean comm_replyCreate(@Valid CommentVO commVO, BindingResult commResult )
			throws Exception {
		logger.info("[COMM_REPLYCREATE_POST: content page ]");
		logger.info("CommentVO : {}", commVO);

		if (commResult.hasErrors()) { // commVO 에 문제가 없을 때
			return false;
		} else {
			service.comm_replyCreate(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_update", method = RequestMethod.POST)
	public @ResponseBody boolean comm_update(@Valid CommentVO commVO, BindingResult commResult )
			throws Exception {
		logger.info("[COMM_UPDATE_POST: content page.]");
		logger.info("CommentVO : {}", commVO);

		if (commResult.hasErrors()) {
			return false;
		} else {
			service.comm_update(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_delete", method = RequestMethod.POST)
	public @ResponseBody boolean comm_delete( CommentVO commVO) throws Exception {
		logger.info("[COMM_DELETE_POST: content page.]");
		logger.info("CommentVO : {}", commVO);
		
		service.comm_delete(commVO);
		logger.info("comm_delete: action.{}", "삭제 함");
		return true;
	}

	@RequestMapping(value = "/board/comm_MaxPage", method = RequestMethod.POST)
	public @ResponseBody ConVO comm_getMaxPage( ConVO conVO ) throws Exception {
		logger.info("[GETMAXCOUNT_POST: getMaxCount.]");
		logger.info("ConVO : {}", conVO);

		return service.comm_getMaxPage(conVO);
	}

	@RequestMapping(value = "/board/getCommentList", method = RequestMethod.POST)
	public @ResponseBody List<CommentVO> getComment( ConVO conVO ) throws Exception {
		logger.info("[GETLIST_POST: comment list.]");
		logger.info("ConVO : {}", conVO);

		List<CommentVO> getList = service.comm_viewList(conVO);

		logger.info(getList.toString());

		return getList;
	}

	@RequestMapping(value = "/downloadFile")
	public void downloadFile(UploadVO vo, HttpServletResponse response) throws Exception {
		String originalFileName = vo.getOriginal_fname();
		String storedFileName = vo.getUpload_fname();

		byte fileByte[] = FileUtils.readFileToByteArray(new File("c:/upload/" + storedFileName));

		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",
				"attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);

		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
	
	@RequestMapping(value = "/excelDownload")
	public String excelDownload( @Valid ConVO vo, BindingResult conResult,  Model model, HttpServletResponse response) throws Exception {
		System.out.println(vo);
		
		List<BoardVO> getList = service.getList(vo);
		model.addAttribute("getList", getList);
		
		return "excelView";
	}
	
	@RequestMapping(value = "/comm_ExcelDownload")
	public String comm_ExcelDownload( @Valid ConVO vo, BindingResult conResult,  Model model, HttpServletResponse response) throws Exception {
//		System.out.println(vo);
		vo.setMaxPage(service.comm_getMaxPage(vo).getMaxPage());
		model.addAttribute("getList", service.comm_viewList(vo));
		
		return "excelView";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {

		Object obj = binder.getTarget();

		if (obj instanceof BoardVO) {
			binder.setValidator(new BoardValidator());
		} else if (obj instanceof ConVO) {
			binder.setValidator(new ConValidator());
		} else if (obj instanceof UploadVO) {
			binder.setValidator(new UploadValidator());
		} else if (obj instanceof CommentVO){
			binder.setValidator(new CommValidator());
		}
	}
}
