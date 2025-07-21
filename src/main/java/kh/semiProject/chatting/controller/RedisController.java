package kh.semiProject.chatting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kh.semiProject.chatting.model.service.RedisService;

@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisService redisService;

    @PostMapping("/save")
    public String saveData(@RequestParam String key, @RequestParam String value) {
        redisService.saveData(key, value, value, value);
        return "Data saved successfully!";
    }
    
    @GetMapping("/get")
    public List<Object> getData(@RequestParam String key) {
        return redisService.getData(key);
    }
}
