package com.smsoft.spring02.controller.board;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.smsoft.spring02.model.board.dto.ReplyVO;
import com.smsoft.spring02.service.board.ReplyService;
import com.smsoft.spring02.service.board.ReplyPager;
 
@RestController
@RequestMapping("/reply/*")
public class ReplyController {
    
    @Inject
    ReplyService replyService;
    
    // 댓글 입력
    @RequestMapping("insert.do")
    public void insert(@ModelAttribute ReplyVO vo,
    		@RequestParam Integer idx, @RequestParam String secretReply, @RequestParam String replytext, 
    		HttpSession session){
		// 세션에 저장된 회원아이디를 댓글작성자에 세팅
        String userId = (String) session.getAttribute("userId");
        vo.setReplyer(userId);
		// 댓글 입력 메서드 호출
        replyService.create(idx, secretReply, replytext);
        
        System.out.println("test : " + vo);
    }
    
    // 댓글 목록(@Controller방식 : veiw(화면)를 리턴)
    @RequestMapping("list.do")
    public ModelAndView list(@RequestParam int idx,
                            @RequestParam(defaultValue="1") int curPage,
                            ModelAndView mav,
                            HttpSession session){
        // **페이징 처리 
        int count = replyService.count(idx); // 댓글 갯수
        ReplyPager replyPager = new ReplyPager(count, curPage);
        int start = replyPager.getPageBegin();
        int end = replyPager.getPageEnd();
        List<ReplyVO> list = replyService.list(idx, start, end, session);
        // 뷰이름 지정
        mav.setViewName("board/replyList");
        // 뷰에 전달할 데이터 지정
        mav.addObject("list", list);
        mav.addObject("replyPager", replyPager);
        // replyList.jsp로 포워딩
        return mav;
    }

	// 댓글 목록(@RestController방식 : Json으로 데이터를 리턴)
	@RequestMapping("listJson.do")
	@ResponseBody // 리턴데이터를 json으로 변환(RestController사용시 @ResponseBody생략가능)
	public List<ReplyVO> listJson(@RequestParam int idx, @RequestParam(defaultValue="1") int curPage, HttpSession session){
		// 페이징 처리
		int count = replyService.count(idx); // 댓글 갯수
		ReplyPager pager = new ReplyPager(count, curPage);
		// 현재 페이지의 페이징 시작 번호
		int start = pager.getPageBegin();
		// 현재 페이지의 페이징  끝 번호
		int end = pager.getPageEnd();
		List<ReplyVO> list = replyService.list(idx, start, end, session);
		// list를 리턴
		return list;
	}
}
 
