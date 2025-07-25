package kh.semiProject.chatting.model.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveData(String id,String content,String nickName,Date date) {
    	Map<String,String> data = new HashMap<>();
    	data.put("date", date.toString());
    	data.put("content", content);
    	data.put("nickName", nickName);
//    	redisTemplate.opsForList().(id, nickName, content,date.toString());/
    	redisTemplate.opsForList().rightPushAll("AllChatting",id, nickName , content , date.toString());
    	redisTemplate.opsForList().rightPushAll(id, nickName , content , date.toString());
//											id, (닉네임,  채팅,   시간)
    }

    public List<Object> getData(String user) {

    	Set<String> keys = redisTemplate.keys(user);
    	Iterator<String> iter = keys.iterator();
    	List<Object> list = new ArrayList<>();
    	while(iter.hasNext()) {
    		List<Object> object = redisTemplate.opsForList().range(iter.next(), 0, -1);
    		list.add(object);
    		System.out.println(list);
    	}
        return list;
    }

}
