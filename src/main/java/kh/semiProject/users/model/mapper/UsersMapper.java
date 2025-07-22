package kh.semiProject.users.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import kh.semiProject.users.model.vo.Users;

@Mapper
public interface UsersMapper {

	// 로그인
	Users login(Users u);

	// 회원가입
	int insertUsers(Users u);
	
	// 아이디 중복확인
	int checkId(String userId);

	// 별명 중복확인
	int checkNickname(String userNickname);
	
	// 회원정보 수정
	int updateUsers(Users u);

	// 탈퇴
	int deleteUsers(Users u);

}