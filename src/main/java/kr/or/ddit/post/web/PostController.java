package kr.or.ddit.post.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.post.model.PostVo;
import kr.or.ddit.post.service.PostServiceInf;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.util.model.PageVo;
import oracle.jdbc.proxy.annotation.Methods;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Resource(name="postService")
	private PostServiceInf postService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	
	/**
	 * Method : postBoardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 번호로 게시글 목록(리스트) 가져오기 
	 */
	
	@RequestMapping("/postBoardList")
	public String postBoardList(Model model, PageVo pageVo, BoardVo boardVo,
						PostVo postVo, HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		
		// left.jsp 에서 게시판이름을 클릭했을때 전의 상태로 돌아가서 
		// 새로고침 효과를 주는 것.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		Map<String, Object> resultMap = postService.postBoardList(pageVo);
	
		String userId = postVo.getUserId();
		String board_name = boardVo.getBoard_name();
		
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int pageCnt = (int) resultMap.get("pageCnt");
		
		model.addAllAttributes(resultMap);
		model.addAttribute("userId",userId);
		model.addAttribute("board_name",board_name);
		return "postList";
	}
	
	/**
	 * Method : postNew
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 목록(리스트) 새글작성 버튼 클릭시 jsp 화면 보이기 
	 */
	@RequestMapping("/postNew")
	public String postNew(Model model, UserVo userVo , PostVo postVo) {
	
		int post_no = postVo.getPost_no();
		model.addAttribute("post_no",post_no);
		
		// left.jsp 에서 게시판이름을 클릭했을때 전의 상태로 돌아가서 
				// 새로고침 효과를 주는 것.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
				
		return "postNew";
	}
	
	/**
	 * Method : postNewSave
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : post로 보낸값을 저장하고 저장후엔 list화면을 보여준다.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/postNewSave",method=RequestMethod.POST)
	public String postNewSave(PostVo postVo ,Model model) throws UnsupportedEncodingException {
		
		String post_title = postVo.getPost_title();
		String post_context = postVo.getPost_context();
		int post_no = postVo.getPost_no();
		
		/*
		postVo.setPost_title(post_title);
		postVo.setPost_context(post_context);*/
		
		int insertpost = postService.insertPost(postVo);
		
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		return "main";
	}
}	



















