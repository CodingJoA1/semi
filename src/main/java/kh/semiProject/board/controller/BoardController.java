package kh.semiProject.board.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import kh.semiProject.board.model.exception.BoardException;
import kh.semiProject.board.model.service.BoardService;
import kh.semiProject.board.model.vo.Attachment;
import kh.semiProject.board.model.vo.Board;
import kh.semiProject.board.model.vo.PageInfo;
import kh.semiProject.common.Pagination;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService bService;
	
//	@GetMapping("home")
//	public String home() {
//		return "index";
//	}
//	
	@GetMapping("noticeBoard")
	public ModelAndView noticeBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "공지사항";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/noticeBoardListPage");
		
		return mv;
	}
	
	
	@GetMapping("licenseBoard")
	public ModelAndView licenseBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "자격증 후기";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/licenseBoardListPage");
		
		return mv;
	}
	
	@GetMapping("studyBoard")
	public ModelAndView studyBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "공부 방법";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/studyBoardListPage");
		
		return mv;
	}
	
	@GetMapping("guidelineBoard")
	public ModelAndView guidelineBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "초보자 가이드라인";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/guidelineBoardListPage");
		
		return mv;
	}
	
	@GetMapping("bestPostBoard")
	public ModelAndView bestPostBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		int listCount = bService.getTopListCount();
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectTopBoardList(pi);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/bestPostBoardListPage");
		
		return mv;
	}
	
	@GetMapping("/{id}/{page}")
	public String selectPost(@PathVariable("id") int bId, @PathVariable("page") int page, Model model) {
		Board b = bService.selectPost(bId);
		ArrayList<Attachment> list = bService.selectAttm(bId); 
		
		if(b != null) {
			model.addAttribute("b", b).addAttribute("page", page).addAttribute("list", list);
			return "views/board/postContent";
		} else {
			throw new BoardException("게시글 상세보기를 실패하였습니다.");
		}
	}
	
	@GetMapping("write")
	public String writePost() {
		return "views/board/postWrite";
	}
	
}
