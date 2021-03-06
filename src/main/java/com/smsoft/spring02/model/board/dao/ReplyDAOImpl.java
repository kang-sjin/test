package com.smsoft.spring02.model.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
 
import com.smsoft.spring02.model.board.dto.ReplyVO;
 
@Repository
public class ReplyDAOImpl implements ReplyDAO {
    @Inject
    SqlSession sqlSession;
    
    // 댓글 목록
    @Override
    public List<ReplyVO> list(Integer idx,  int start, int end) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("idx", idx);
        map.put("start", start);
        map.put("end", end);
        return sqlSession.selectList("reply.listReply", map);
    }
    // 댓글 작성
    /*@Override
    public void create(ReplyVO vo) {
        sqlSession.insert("reply.insertReply", vo);
    }*/
    
    @Override
    public void create(int idx, String secretReply, String replytext) {
    	
    	 Map<String, Object> map = new HashMap<String, Object>();
         map.put("idx", idx);
         map.put("secretReply", secretReply);
         map.put("replytext", replytext);
    	
        sqlSession.insert("reply.insertReply", map);
    }
    
    // 댓글 수정
    @Override
    public void update(ReplyVO vo) {
        // TODO Auto-generated method stub
 
    }
    // 댓글 삭제
    @Override
    public void delete(Integer rno) {
        // TODO Auto-generated method stub
 
    }
    // 댓글 갯수
    @Override
    public int count(int idx) {
        return sqlSession.selectOne("reply.countReply",idx);
    }
}