package kh.semiProject.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ChattingController {

	@GetMapping("/chatting")
	public String chatting(HttpSession session) {
//		System.out.println(session.getAttribute("loginUser"));
		return "/chatting/chat";
	}
	
	@GetMapping("getBeforeChatting")
	public void save() {
		
	}
	
}
