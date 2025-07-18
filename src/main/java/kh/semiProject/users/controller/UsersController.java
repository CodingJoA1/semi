package kh.semiProject.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import kh.semiProject.users.model.exception.UsersException;
//import kh.semiProject.users.model.service.EmailService;
import kh.semiProject.users.model.service.UsersService;
import kh.semiProject.users.model.vo.Users;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@SessionAttributes("loginUser")
@RequestMapping("/users") // 공용 url
public class UsersController {
	
	private final UsersService uService;
//	@Autowired
//	private EmailService emailService;
	
	// 로그인 화면 이동
	@GetMapping("/signIn")
	public String signIn() {
		return "views/users/login";
	}
	
	// 로그인
	@PostMapping("/signIn")
	public String login(Users u, HttpSession session) {
	    Users loginUser = uService.login(u);
	    
	    if (loginUser != null && u.getUserPassword().equals(loginUser.getUserPassword())) {
	        session.setAttribute("loginUser", loginUser);
	        return "views/mainPage/mainPage(User)";
	    } else {
	        throw new UsersException("로그인을 실패했습니다.");
	    }
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:/";
	}
	
	// 회원가입 화면 이동
	@GetMapping("/enroll")
	public String enroll() {
		return "views/users/enroll";
	}
	
	// 회원가입
	@PostMapping("/enroll")
	public String insertUsers(@ModelAttribute Users u) {
		int result = uService.insertUsers(u);
		if(result > 0) {
			return "redirect:/";
		} else {
			throw new UsersException("회원가입을 실패했습니다.");
		}
	}
	
	// 아이디 중복확인
	@GetMapping("/checkId")
	@ResponseBody
	public String checkId(@RequestParam("userId") String userId) {
	    int count = uService.checkId(userId);
	    if(count > 0) {
	        return "FAIL";
	    } else {
	        return "OK";
	    }
	}
	
	// 닉네임 중복확인
	@GetMapping("/checkNickname")
	@ResponseBody
	public String checkNickname(@RequestParam("userNickname") String userNickname) {
	    int count = uService.checkNickname(userNickname);
	    if(count > 0) {
	        return "FAIL";
	    } else {
	        return "OK";
	    }
	}
	
	// 이메일 인증
	// 인증번호 전송
//    @PostMapping("/sendCode")
//    public ResponseEntity<?> sendCode(@RequestParam("email") String email, HttpSession session) {
//        String code = emailService.sendVerificationEmail(email);
//        session.setAttribute("authCode", code);
//        return ResponseEntity.ok("인증번호 전송 완료");
//    }
//
//    // 인증번호 확인
//    @PostMapping("/verifyCode")
//    public ResponseEntity<?> verifyCode(@RequestParam String code, HttpSession session) {
//        String savedCode = (String) session.getAttribute("authCode");
//        if (savedCode != null && savedCode.equals(code)) {
//            return ResponseEntity.ok("인증 성공");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("인증 실패");
//        }
//    }
//	
	// 마이페이지 화면 이동
	@GetMapping("/myInfo")
	public String myInfo() {
		return "views/users/myInfo";
	}
	
	// 회원정보 수정 화면 이동
	@GetMapping("/edit")
	public String edit() {
		return "views/users/edit";
	}
	
	// 회원 정보 수정
	@PostMapping("/edit")
	public String updateUsers(@RequestParam("currentPassword") String currentPassword,
	                          @RequestParam("newPassword") String newPassword,
	                          @ModelAttribute Users u,
	                          HttpSession session, Model model) {
	    Users loginUser = (Users) session.getAttribute("loginUser");
	    
	    // 현재 비밀번호 확인
	    if (!loginUser.getUserPassword().equals(currentPassword)) {
	        model.addAttribute("errorMsg", "회원정보 수정을 실패했습니다.");
	        return "views/users/edit";
	    }

	    u.setUserId(loginUser.getUserId());
	    u.setUserPassword(newPassword);  // 새 비밀번호 설정
	    u.setCurrentPassword(currentPassword);

	    int result = uService.updateUsers(u);
	    if(result > 0) {
	        // 세션에 정보 업데이트
	    	Users updatedUser = uService.login(u);
	    	model.addAttribute("loginUser", updatedUser);
	        session.setAttribute("loginUser", u);
	        return "redirect:/users/myInfo";
	    } else {
	        model.addAttribute("errorMsg", "회원정보 수정을 실패했습니다.");
	        return "views/users/edit";
	    }
	}
	
	// 탈퇴
	@PostMapping("/delete")
	public String deleteUsers(@RequestParam("currentPassword") String currentPassword,
	                          @ModelAttribute Users u, SessionStatus status,
	                          HttpSession session, Model model) {

	    Users loginUser = (Users) session.getAttribute("loginUser");

	    if (!loginUser.getUserPassword().equals(currentPassword)) {
	        model.addAttribute("errorMsg", "회원 탈퇴를 실패했습니다.");
	        return "views/users/myInfo";
	    }

	    u.setUserId(loginUser.getUserId());

	    int result = uService.deleteUsers(u);
	    if(result > 0) {
	    	status.setComplete();
	    	session.invalidate(); // 세션 종료
	        return "home";
	    } else {
	        model.addAttribute("errorMsg", "회원 탈퇴를 실패했습니다.");
	        return "views/users/myInfo";
	    }
	}
}
