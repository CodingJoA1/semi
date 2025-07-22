package kh.semiProject.chatting.model.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveData(String key, String value1,String value2,Date value3) {
        long list = redisTemplate.opsForList().rightPushAll(	key,value1,value2,value3);
        //														id, 닉네임,  채팅,   시간
        System.out.println(list);
    }

    public List<Object> getData() {

    	System.out.println("1");
    	String key = "";
    	System.out.println("2");
    	List<Object> list = redisTemplate.opsForList().range(key, 0, -1);
    	//													* 키 전체 조회
    	System.out.println("3");
    	System.out.println(list);
        return null;
    }
}
