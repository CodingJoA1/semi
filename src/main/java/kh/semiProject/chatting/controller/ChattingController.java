package kh.semiProject.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ChattingController {

	
	@GetMapping("/chatting")
	public String chatGET() {
		return "/views/chatting/chat";
	}
}
