package kh.semiProject.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	// 따로 지정하지 않으면 get
	@GetMapping("/")
	public String calendar() {
		return "/calendar/main";
	}
	
	@GetMapping("/bCalendar")
	public String bCalendar() {
		return "/calendar/calendar";
	}
}
