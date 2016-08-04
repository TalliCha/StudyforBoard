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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ojt.test.domain.BoardVO;
import ojt.test.domain.BoardValidator;
import ojt.test.domain.CommValidator;
import ojt.test.domain.CommentVO;
import ojt.test.domain.ConVO;
import ojt.test.domain.ConValidator;
import ojt.test.domain.UploadVO;
import ojt.test.service.BoardService;

@Controller
public class BoardController {

	@Inject
	BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String indexGET() {
		logger.info("[CON_GET] /:: move /board");
		return "redirect:/board";
	}

	@RequestMapping(value = "/board", method = RequestMethod.GET)
	public String viewListGET(@Valid ConVO conVO, BindingResult conResult) {
		logger.info("[CON_GET] /board: list page.");

		if (conResult.hasErrors()) {
			logger.info("	#set init: ConVO : {}", conVO);
		} else {

			logger.info("	#get: ConVO : {}", conVO);
		}
		return "/board/list";
	}

	@RequestMapping(value = "/board", method = RequestMethod.POST)
	public String viewListPOST(@Valid ConVO conVO, BindingResult conResult) {
		logger.info("[CON_POST] /board:: list page.");

		if (conResult.hasErrors()) {
			logger.info("	#set init: ConVO :{}", conVO);
		} else {
			logger.info("	#get: ConVO : {}", conVO);
		}
		return "/board/list";
	}

	@RequestMapping(value = "/board/getList", method = RequestMethod.POST)
	public @ResponseBody List<BoardVO> getList(ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[CON_POST] /board/getList:: get boardList.");
		logger.info("	#get: ConVO : {}", conVO);

		List<BoardVO> getList = service.getList(conVO);

		logger.info("	#get : getList : {}", getList);

		return getList;
	}

	@RequestMapping(value = "/board/getMaxPage", method = RequestMethod.POST)
	public @ResponseBody ConVO getMaxPage(ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[CON_POST] /board/getMaxPage:: get MaxCount.");
		logger.info("	#get : ConVO : {}", conVO);

		return service.getMaxPage(conVO);
	}

	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(ConVO conVO) {
		logger.info("[CON_POST] /board/write:: content page.]");
		logger.info("	#get: ConVO : {}", conVO);

		return "/board/write";
	}

	@RequestMapping(value = "/board/create", method = RequestMethod.POST)
	public String create(ConVO conVO, @Valid BoardVO boVO, BindingResult boResult, Model model,
			MultipartHttpServletRequest request, RedirectAttributes redirectAttr) throws Exception {
		logger.info("[CON_POST] /board/create:: create content.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		List<MultipartFile> file_list = ((MultipartHttpServletRequest) request).getFiles("uploadFile");

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/write";
		} else {
			service.create(boVO, file_list);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}
	}

	@RequestMapping(value = "/board/reply", method = RequestMethod.POST)
	public String reply(BoardVO boVO, ConVO conVO) throws Exception {
		logger.info("[CON_POST] /board/reply:: reply page.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		return "/board/reply";
	}

	@RequestMapping(value = "/board/replycreate", method = RequestMethod.POST)
	public String replyCreate(HttpServletRequest request, @Valid BoardVO boVO, BindingResult boResult, ConVO conVO,
			Model model, RedirectAttributes redirectAttr) throws Exception {
		logger.info("[CON_POST] /board/replycreate:: reply content.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		List<MultipartFile> file_list = ((MultipartHttpServletRequest) request).getFiles("uploadFile");

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/reply";
		} else {
			service.replyCreate(boVO, file_list);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}

	}

	@RequestMapping(value = "/board/page", method = RequestMethod.GET)
	public String pageGET(BoardVO boVO, UploadVO upVO, @Valid ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[CON_GET] /board/page:: content page.]");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		service.viewCount(boVO); // 글자 조회 1추가
		service.viewContent(boVO);
		return "/board/page";
	}

	@RequestMapping(value = "/board/page", method = RequestMethod.POST)
	public String pagePOST(BoardVO boVO, @Valid ConVO conVO, BindingResult conResult) throws Exception {
		logger.info("[CON_POST] /board/page:: content page.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		service.viewCount(boVO); // 글자 조회 1추가
		service.viewContent(boVO);

		return "/board/page";
	}

	@RequestMapping(value = "/board/delete", method = RequestMethod.POST)
	public String delete(BoardVO boVO, ConVO conVO, RedirectAttributes redirectAttr) throws Exception {
		logger.info("[CON_POST] /board/delete:: delete content.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		service.delete(boVO);
		// 리다이렉트 경우 값 전달
		redirectAttr.addFlashAttribute("conVO", conVO);
		redirectAttr.addFlashAttribute("sendMsg", "delSucc");
		return "redirect:/board";
	}

	@RequestMapping(value = "/board/modify", method = RequestMethod.POST)
	public String modify(BoardVO boVO, ConVO conVO) throws Exception {
		logger.info("[CON_POST] /board/modify:: modify page.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		service.viewContent(boVO);

		return "/board/modify";
	}

	@RequestMapping(value = "/board/update", method = RequestMethod.POST)
	public String update(MultipartHttpServletRequest request, @Valid BoardVO boVO, BindingResult boResult, ConVO conVO,
			Model model, RedirectAttributes redirectAttr) throws Exception {
		logger.info("[CON_POST] /board/update:: update content.");
		logger.info("	ConVO : {}", conVO);
		logger.info("	BoardVO : {}", boVO);

		List<MultipartFile> file_list = ((MultipartHttpServletRequest) request).getFiles("uploadFile");

		// 주의할점
		// MultipartResolver는 파일이 업로드 되지 않을 경우 null 을 리턴하는 것이 아니라 "" (공백) 을 리턴

		if (boResult.hasErrors()) {
			model.addAttribute("sendMsg", "hasErr");
			return "/board/modify";
		} else {
			service.update(boVO, file_list);
			redirectAttr.addFlashAttribute("conVO", conVO);
			return "redirect:/board/page?bno=" + boVO.getBno();
		}
	}

	/* Comment */

	@RequestMapping(value = "/board/comm_create", method = RequestMethod.POST)
	public @ResponseBody boolean comm_create(@Valid CommentVO commVO, BindingResult commResult) throws Exception {
		logger.info("[CON_POST] /board/comm_create:: create comm.");
		logger.info("	CommentVO : {}", commVO);

		if (commResult.hasErrors()) { // commVO 에 문제가 없을 때
			return false;
		} else {
			service.comm_create(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_replycreate", method = RequestMethod.POST)
	public @ResponseBody boolean comm_replyCreate(@Valid CommentVO commVO, BindingResult commResult) throws Exception {
		logger.info("[CON_POST] /board/comm_replycreate:: reply comm.");
		logger.info("	CommentVO : {}", commVO);

		if (commResult.hasErrors()) { // commVO 에 문제가 없을 때
			return false;
		} else {
			service.comm_replyCreate(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_update", method = RequestMethod.POST)
	public @ResponseBody boolean comm_update(@Valid CommentVO commVO, BindingResult commResult) throws Exception {
		logger.info("[CON_POST] /board/comm_update:: update comm");
		logger.info("	CommentVO : {}", commVO);

		if (commResult.hasErrors()) {
			return false;
		} else {
			service.comm_update(commVO);
			return true;
		}
	}

	@RequestMapping(value = "/board/comm_delete", method = RequestMethod.POST)
	public @ResponseBody boolean comm_delete(CommentVO commVO) throws Exception {
		logger.info("[CON_POST] /board/comm_delete:: delete comm.");
		logger.info("	CommentVO : {}", commVO);

		service.comm_delete(commVO);
		return true;
	}

	@RequestMapping(value = "/board/comm_MaxPage", method = RequestMethod.POST)
	public @ResponseBody ConVO comm_getMaxPage(ConVO conVO) throws Exception {
		logger.info("[CON_POST] /board/comm_MaxPage:: get comm_count.]");
		logger.info("	ConVO : {}", conVO);

		return service.comm_getMaxPage(conVO);
	}

	@RequestMapping(value = "/board/getCommentList", method = RequestMethod.POST)
	public @ResponseBody List<CommentVO> getComment(@Valid ConVO conVO, BindingResult commResult) throws Exception {
		logger.info("[CON_POST] /board/getCommentList:: get comment list.]");
		logger.info("	ConVO : {}", conVO);

		List<CommentVO> getList = service.comm_viewList(conVO);

		logger.info("	#get: comm_list {}", getList.toString());

		return getList;
	}

	@RequestMapping(value = "/downloadFile")
	public void downloadFile(UploadVO vo, HttpServletResponse response) throws Exception {
		logger.info("[CON_GET] /downloadFile:: downloadFile.]");
		logger.info("	UploadVO : {}", vo);

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
	public String excelDownload(@Valid ConVO vo, BindingResult conResult, Model model, HttpServletResponse response)
			throws Exception {
		logger.info("[CON_GET] /excelDownload:: excelDownload.]");
		logger.info("	ConVO : {}", vo);

		List<BoardVO> getList = service.getList(vo);
		model.addAttribute("getList", getList);

		return "excelView";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		Object obj = binder.getTarget();

		if (obj instanceof BoardVO) {
			logger.info("[VALIDATION_CHECK]: {}", "BoardVO");
			binder.setValidator(new BoardValidator());
		} else if (obj instanceof ConVO) {
			logger.info("[VALIDATION_CHECK]: {}", "ConVO");
			binder.setValidator(new ConValidator());
		} else if (obj instanceof CommentVO) {
			logger.info("[VALIDATION_CHECK]: {}", "CommentVO");
			binder.setValidator(new CommValidator());
		}
	}
}
