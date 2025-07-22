package kh.semiProject.chatting.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.semiProject.chatting.model.service.RedisService;

@RequestMapping("/redis")
@Controller
public class RedisController {
	
    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public String saveData(
    		@RequestParam("cId") String key, 
    		@RequestParam("cContent") String value1, 
    		@RequestParam("cNickName") String value2, 
    		@RequestParam("cDate") Date value3 ) {
    	System.out.println("aaaaaaaaaaaaaaaaaaaaa");
        redisService.saveData(key, value1, value2, value3);
        return "Data saved successfully!";
    }
    
    @GetMapping("/get")
    @ResponseBody
    public List<Object> getData() {
        return redisService.getData();
    }
}
