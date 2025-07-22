package kh.semiProject.users.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Users {
	private String userId;
	private String userPassword;
	private String currentPassword;
	private String userName;
	private String userNickname;
	private String userEmail;
	private String userBlock;
	private Date createDate;
	private String createType;
	private String admin;
	private String userStatus;
}