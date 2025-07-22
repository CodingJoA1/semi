package kh.semiProject.users.model.service;

import org.springframework.stereotype.Service;

import kh.semiProject.users.model.mapper.UsersMapper;
import kh.semiProject.users.model.vo.Users;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {
	
	private final UsersMapper mapper;
	
	// 로그인
	public Users login(Users u) {
		return mapper.login(u);
	}

	// 회원가입
	public int insertUsers(Users u) {
		return mapper.insertUsers(u);
	}
	
	// 아이디 중복확인
	public int checkId(String userId) {
		return mapper.checkId(userId);
	}

	// 별명 중복확인
	public int checkNickname(String userNickname) {
		return mapper.checkNickname(userNickname);
	}
	
	// 회원정보 수정
	public int updateUsers(Users u) {
		return mapper.updateUsers(u);
	}

	// 탈퇴
	public int deleteUsers(Users u) {
		return mapper.deleteUsers(u);
	}
}
