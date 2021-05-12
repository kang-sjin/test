package com.smsoft.spring02.service.board;
 
import java.util.List;
 
import javax.servlet.http.HttpSession;
 
import com.smsoft.spring02.model.board.dao.BoardDAO;
import com.smsoft.spring02.model.board.dto.BoardVO;
 
public interface BoardService {
    // 01. 게시글 작성
    public void create(BoardVO vo) throws Exception;
    // 02. 게시글 상세보기
    public BoardVO read(int idx) throws Exception;
    // 03. 게시글 수정
    public void update(BoardVO vo) throws Exception;
    // 04. 게시글 삭제
    public void delete(int idx) throws Exception;
    // 05. 게시글 전체 목록
    public List<BoardVO> listAll(int start, int end, String searchOption, String keyword) throws Exception;
    // 06. 게시글 조회
    public void increaseViewcnt(int idx, HttpSession session) throws Exception;
    // 07. 게시글 레코드 갯수 메서드 추가
    public int countArticle(String searchOption, String keyword) throws Exception;
}