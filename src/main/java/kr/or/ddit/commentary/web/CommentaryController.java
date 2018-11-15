package kr.or.ddit.commentary.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.commentary.model.CommentaryVo;
import kr.or.ddit.commentary.service.CommentaryServiceInf;
import kr.or.ddit.post.service.PostServiceInf;

@Controller
@RequestMapping("/commentary")
public class CommentaryController {
	
	@Resource(name="postService")
	private PostServiceInf postService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Resource(name="commentaryService")
	private CommentaryServiceInf commentaryService;
	
	
	@RequestMapping(value="/commentaryInsert",method=RequestMethod.POST)
	public String commentaryList(HttpServletRequest request, Model model,CommentaryVo comment, 
								@RequestParam(value="post_no")int post_no) {
		
		String comm_context = request.getParameter("comment");
		String comm_user = request.getParameter("comm_user");
		
		comment.setComm_context(comm_context);
		comment.setComm_user(comm_user);
		comment.setComm_post(post_no);
		
		int comm = commentaryService.insertComment(comment);
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
				
		// redirect ==> 그화면으로 바로 보내준다.
		return "redirect:/post/postDetail?post_no="+post_no;
	}
}
