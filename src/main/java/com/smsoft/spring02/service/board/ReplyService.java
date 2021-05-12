package com.smsoft.spring02.service.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.smsoft.spring02.model.board.dto.ReplyVO;
 
public interface ReplyService {
    // 댓글 목록
    public List<ReplyVO> list(Integer idx, int start, int end, HttpSession session);
    // 댓글 입력
    
    /*public void create(ReplyVO vo);*/
    public void create(Integer idx, String secretReply, String replytext);
    
    // 댓글 수정
    public void update(ReplyVO vo);
    // 댓글 삭제
    public void delete(Integer rno);
    // 댓글 갯수
    public int count(int idx);
}