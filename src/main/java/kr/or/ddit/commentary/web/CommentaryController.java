	


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
import kr.or.ddit.post.model.PostVo;
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
	
	
	/**
	 * Method : commentaryInsert
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param request
	 * @param model
	 * @param comment
	 * @param postVo
	 * @return
	 * Method 설명 : 댓글 생성
	 */
	@RequestMapping(value="/commentaryInsert",method=RequestMethod.POST)
	public String commentaryInsert(HttpServletRequest request, Model model,CommentaryVo comment, 
								@RequestParam(value="post_no") int post_no, PostVo postVo) {
	
		String comm_context = request.getParameter("comment");
		String comm_user = request.getParameter("comm_user");
		
		comment.setComm_context(comm_context);
		comment.setComm_user(comm_user);
		comment.setComm_post(post_no);
		int comm = commentaryService.insertComment(comment);
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		model.addAttribute("post_no",post_no);
		model.addAttribute("comm_user",comm_user);
		
				
		// redirect ==> 그화면으로 바로 보내준다.
		return "redirect:/post/postDetail?post_no="+post_no;
	}
	
	
	/**
	 * Method : commentaryDelete
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 댓글 삭제 
	 */
	@RequestMapping(value="/commentaryDelete", method=RequestMethod.POST)
	public String commentaryDelete(HttpServletRequest request, Model model, CommentaryVo comment ,PostVo postVo) {
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		
		int comm_post = postVo.getPost_no();
		int post_no = postVo.getPost_no();
		int comm_no = comment.getComm_no();
		
		int comm = commentaryService.deleteComment(comm_no);
		
		model.addAttribute("comm_post",comm_post);
		model.addAttribute("comm_no",comm_no);
		
		
		return "redirect:/post/postDetail?comm_post="+comm_post+"&post_no="+post_no+"&comm_no="+comm_no;
	}
}
