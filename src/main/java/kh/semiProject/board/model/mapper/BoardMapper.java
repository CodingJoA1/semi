package kh.semiProject.board.model.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import kh.semiProject.board.model.vo.Attachment;
import kh.semiProject.board.model.vo.Board;

@Mapper
public interface BoardMapper {

	int getListCount(String category);

	ArrayList<Board> selectBoardList(String category, RowBounds rowBounds);

	int getTopListCount();

	ArrayList<Board> selectTopBoardList(Object object, RowBounds rowBounds);

	Board selectPost(int bId);

	ArrayList<Attachment> selectAttm(int bId);

}
