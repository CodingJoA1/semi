package kh.semiProject.chatting.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kh.semiProject.chatting.model.service.RedisService;

@RequestMapping("/redis")
@Controller
public class RedisController {
	
    @Autowired
    private RedisService redisService;
    
    @GetMapping("/save")
    @ResponseBody
    public String saveData(
    		@RequestParam("cId") String id, 
    		@RequestParam("cContent") String content, 
    		@RequestParam("cNickName") String nickName,
    		@RequestParam("date") String date
    		) {
        redisService.saveData(id, content, nickName , date);
        return "save";
    }
    
    @GetMapping("/get")
    @ResponseBody
    public List<Object> getData(@RequestParam("user") String user) {
        List<Object> list =  redisService.getData(user); 
        System.out.println(list);
    	return list;
    }
}
