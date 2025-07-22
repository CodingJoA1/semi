package kh.semiProject.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kh.semiProject.board.model.exception.BoardException;
import kh.semiProject.board.model.service.BoardService;
import kh.semiProject.board.model.vo.Attachment;
import kh.semiProject.board.model.vo.Board;
import kh.semiProject.board.model.vo.PageInfo;
import kh.semiProject.common.Pagination;
import kh.semiProject.users.model.vo.Users;
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
		
		String category = "noticeBoard";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/noticeBoardListPage");
		
		return mv;
	}
	
	
	@GetMapping("licenseBoard")
	public ModelAndView licenseBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "licenseBoard";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/licenseBoardListPage");
		
		return mv;
	}
	
	@GetMapping("studyBoard")
	public ModelAndView studyBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "studyBoard";
		
		int listCount = bService.getListCount(category);
		
		PageInfo pi = Pagination.getPageInfo(currentPage, listCount, 10);
		
		ArrayList<Board> list = bService.selectBoardList(pi, category);
		
		mv.addObject("list", list).addObject("pi", pi).addObject("loc", request.getRequestURI()).setViewName("views/board/studyBoardListPage");
		
		return mv;
	}
	
	@GetMapping("guidelineBoard")
	public ModelAndView guidelineBoard(@RequestParam(value="page", defaultValue="1") int currentPage, ModelAndView mv, HttpServletRequest request) {
		
		String category = "guidelineBoard";
		
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
	
	// 메인 화면 인기글 조회
	@GetMapping("bestTop")
	@ResponseBody
	public String selectTop(HttpServletResponse response) {
		ArrayList<Board> list = bService.selectTop();
		
		// json 버전
//		JSONArray array = new JSONArray();
//		for(Board b : list) {
//			JSONObject json = new JSONObject();
//			json.put("postNo", b.getPostNo());
//			json.put("postDate", b.getPostDate());
//			json.put("postTitle", b.getPostTitle());
//			json.put("userId", b.getUserId());
//			json.put("postViews", b.getPostViews());
//			
//			array.put(json);
//		}
		
		// gson 버전
		response.setContentType("application/json; charset=UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		return gson.toJson(list);
	}
	
	// 게시글 상세 조회
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
	
	// 게시글 작성 완료 버튼 누른 후
	@PostMapping("insert")
	public String insertPost(@ModelAttribute Board b, @RequestParam("file") ArrayList<MultipartFile> files, HttpSession session) {
		System.out.println(files);
		String id = ((Users)session.getAttribute("loginUser")).getUserId();
		b.setUserId(id);
		
		// MultipartFile로 받아온 file을 Attachment로 바꿔주는 작업
		ArrayList<Attachment> list = new ArrayList<Attachment>();
		for(int i = 0; i < files.size(); i++) {
			MultipartFile upload = files.get(i); // files에 담겨있던 file을 MultipartFile 타입의 upload에 대입
			if(!upload.getOriginalFilename().equals("")) {
				String[] returnArr = saveFile(upload); // saveFile메소드 호출, 리턴값이 String배열이기 때문에 String[]에 담아줌
				if(returnArr[1] != null) { // file의 rename이 null이 아닐때 즉 있을 때
					Attachment a = new Attachment();
					a.setAttachmentName(upload.getOriginalFilename()); // file의 오리지널네임 세팅
					a.setAttmRename(returnArr[1]); // 리네임 세팅
					a.setAttmPath(returnArr[0]); // 경로 세팅
					
					list.add(a); // ArrayList에 Attachment 추가
				}
			}
		}
		int result1 = 0;
		int result2 = 0;
		
		if(list.isEmpty()) {
			result1 = bService.insertPost(b);
		} else {
			result1 = bService.insertPost(b);
			for(Attachment a : list) {
				a.setPostNo(b.getPostNo());
			}
			result2 = bService.insertAttm(list);
			
		}
		
		if(result1 + result2 == list.size() + 1) {
			return "redirect:/board/" + b.getCategory();
		} else {
			for(Attachment a : list) {
				deleteFile(a.getAttmRename());
			}
			throw new BoardException("게시글 작성을 실패하였습니다.");
		}
		
	}

	public String[] saveFile(MultipartFile upload) {
		
		// 파일 저장소 지정
		String savePath = "c:\\uploadFilesSemi";
		
		// 폴더 생성
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		// file이름 rename 과정
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		int ranNum = (int)(Math.random()*100000);
		String originFileName = upload.getOriginalFilename();
		String renameFileName = sdf.format(new Date()) + ranNum + originFileName.substring(originFileName.lastIndexOf("."));
		
		// file 저장
		String renamePath = folder + "\\" + renameFileName;
		try {
			upload.transferTo(new File(renamePath));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
		
		// String[]에 담아 리턴
		String[] returnArr = new String[2];
		returnArr[0] = savePath;
		returnArr[1] = renameFileName;
		
		return returnArr;
	}
	
	public void deleteFile(String attmRename) {
		String savePath = "c:\\uploadFilesSemi";
		
		File f = new File(savePath + "\\" + attmRename);
		if(f.exists()) {
			f.delete();
		}
	}
}
