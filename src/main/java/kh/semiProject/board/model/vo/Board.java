package kh.semiProject.board.model.vo;

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
public class Board {
	private int postNo;
	private String postTitle;
	private Date postDate;
	private String postContent;
	private int postViews;
	private String category;
	private String userId;
	private String userNickname;
	private String postStatus;
	private int postRecommend;
}
