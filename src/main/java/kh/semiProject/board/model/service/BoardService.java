package kh.semiProject.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import kh.semiProject.board.model.mapper.BoardMapper;
import kh.semiProject.board.model.vo.Attachment;
import kh.semiProject.board.model.vo.Board;
import kh.semiProject.board.model.vo.PageInfo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

	private final BoardMapper mapper;
	
	public int getListCount(String category) {
		return mapper.getListCount(category);
	}

	public ArrayList<Board> selectBoardList(PageInfo pi, String category) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectBoardList(category, rowBounds);
	}

	public int getTopListCount() {
		return mapper.getTopListCount();
	}

	public ArrayList<Board> selectTopBoardList(PageInfo pi) {
		int offset = (pi.getCurrentPage()-1)*pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return mapper.selectTopBoardList(null, rowBounds);
	}

	public Board selectPost(int bId) {
		return mapper.selectPost(bId);
	}

	public ArrayList<Attachment> selectAttm(int bId) {
		return mapper.selectAttm(bId);
	}

}
