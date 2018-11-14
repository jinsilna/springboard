package kr.or.ddit.post.web;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Insert;
import org.springframework.http.HttpRequest;
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
import kr.or.ddit.util.model.PageVo;

/**
 * PostController.java
 *
 * @author pc07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정자 수정내용
 * ------ ------------------------
 * pc07 최초 생성
 *
 * </pre>
 */
/**
 * PostController.java
 *
 * @author pc07
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정자 수정내용
 * ------ ------------------------
 * pc07 최초 생성
 *
 * </pre>
 */
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Resource(name="postService")
	private PostServiceInf postService;
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	@Resource(name="commentaryService")
	private CommentaryServiceInf commentaryService;

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
		String board_no = boardVo.getBoard_no();
		
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int pageCnt = (int) resultMap.get("pageCnt");
		
		model.addAllAttributes(resultMap);
		model.addAttribute("userId",userId);
		model.addAttribute("board_name",board_name);
		model.addAttribute("board_no", board_no);
		return "postList";
	}
	
	
	
	/**
	 * Method : postPageListAjax
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param model
	 * @param pageVo
	 * @return
	 * Method 설명 : ajax
	 */
	@RequestMapping("/postPageListAjax")
	public String postPageListAjax(Model model, PageVo pageVo, @RequestParam(value="board_no")int board_no) {
		
		Map<String, Object> resultMap = postService.postBoardList(pageVo);
		
		model.addAllAttributes(resultMap);
		return "jsonView";
		
	}
	
	/**
	 * Method : postNew
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 목록(리스트) 새글작성 버튼 클릭시 jsp 화면 보이기 
	 */
	@RequestMapping("/postNew")
	public String postNew(Model model , PostVo postVo, @RequestParam(value="board_no")int board_no) {
		// post_no 값 넣어주기
		int post_no = postVo.getPost_no();
		// 넘기려고 담아주기 
		model.addAttribute("post_no",post_no);
		model.addAttribute("post_board",board_no);
		
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
		
		int insertpost = postService.insertPostNo(postVo);
		
		// 새로고침 효과 
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		return "postDetail";
	}
	
	/**
	 * Method : postDetail
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param model
	 * @return
	 * Method 설명 : 게시글 상세 가져오기
	 */
	@RequestMapping(value="/postDetail",method=RequestMethod.GET)
	public String postDetail(Model model, @RequestParam(value="post_no")int post_no) {
		
		PostVo postVo = postService.selectOnePost(post_no);
		model.addAttribute("postVo", postVo);
		
		List<CommentaryVo> commList = commentaryService.selectPostComment(post_no);
		model.addAttribute("commList",commList);
		
		// left.jsp 계속 나오게 하는거.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		return "postDetail";
	}
	
	/**
	 * Method : postUpdate
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 수정-->  jsp로 넘겨주기 
	 */
	@RequestMapping("/postUpdateView")
	public String postUpdateView(Model model, @RequestParam(value="post_no")int post_no) {
		
		PostVo postVo = postService.selectOnePost(post_no);
		model.addAttribute("postVo", postVo);
		
		// 새로고침 효과 
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		return "postUpdate";
	}
	
	
	/**
	 * Method : postDelete
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 수정
	 *				 Model 객체 값을 넣어줄때 사용한다. 	
	 */

	@RequestMapping(value="/postUpdate",method=RequestMethod.POST)
	public String postUpdate(Model model, PostVo postVo) {
		
		// update
		int postUpdate = postService.updatePost(postVo);
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		// 상세화면으로 넘겨준다.
		return "postDetail";
		
	}
	
	/**
	 * Method : postDelete
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * GET 방식으로 온다.
	 * Method 설명 : 게시글 삭제 --
	 * 				 로그인 한 아이디와 post입력한 아이디가 동일하면 
	 * 				 삭제버튼을 누를수 있고, 동일한 사람이 눌렀을경우 실제로 삭제가 되는게 아니고 
	 * 				 '삭제되었습니다.' 글이 나오게 
	 */
	@RequestMapping("/postDelete")
	public String postDelete(Model model ,@RequestParam(value="post_no")int post_no) {
		
		int postDelete = postService.deletePost(post_no);		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("post_no" ,post_no);
		model.addAttribute("boardUserList", boardUserList);
		
		return "postDetail";
		
	}
	
	/**
	 * Method : postSearch
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 검색 기능 
	 * ********************* 아직 구현하지 못했음.********************************
	 */
	@RequestMapping(value="/postSearch",method=RequestMethod.GET)
	public String postSearch() {
		
		return"";
	}
	/******************************************************************************/
	
	/**
	 * Method : postComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시글 답글 jsp 로 넘겨주는곳.
	 */
	@RequestMapping(value="/postCommentView",method=RequestMethod.GET)
	public String postCommentView(Model model ,PostVo postVo 
										,@RequestParam(value="post_pid")int post_pid
										,@RequestParam(value="post_no")int post_no) {
		
		System.out.println("01 " + postVo.getPost_no());
		System.out.println("02 " + postVo.getPost_board());
		System.out.println("03 " + postVo.getPost_context());
		System.out.println("04" + postVo.getPost_pid());
		System.out.println("05 " + postVo.getPost_rmv());
		System.out.println("06 " + postVo.getPost_title());
		
		model.addAttribute("post_pid",post_pid);
		return "postCommNew";
	}
	
	/**
	 * Method : postComment
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param commentaryVo
	 * @return
	 * Method 설명 : 게시글 답글 생성부분 detail화면으로 간다.
	 */  
	@RequestMapping(value="/postComment",method=RequestMethod.POST)
	public String postComment(PostVo postVo, Model model,
								@RequestParam(value="post_no")int post_no,
							    @RequestParam(value="post_pid")int post_pid) {
		System.out.println("1 " + postVo.getPost_no());
		System.out.println("2 " + postVo.getPost_board());
		System.out.println("3 " + postVo.getPost_context());
		System.out.println("4" + postVo.getPost_pid());
		System.out.println("5 " + postVo.getPost_rmv());
		System.out.println("6" + postVo.getPost_title());
		
		int insertcomment = postService.insertPost(postVo);
		System.out.println("insertcomm");
		
		// 새로고침 효과 
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		return "postDetail";
	}
	
	
}	









