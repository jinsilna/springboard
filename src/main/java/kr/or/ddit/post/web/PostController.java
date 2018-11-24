package kr.or.ddit.post.web;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.attach.model.AttachVo;
import kr.or.ddit.attach.service.AttachServiceInf;
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
 *      <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정자 수정내용
 * ------ ------------------------
 * pc07 최초 생성
 *
 *      </pre>
 */
@Controller
@RequestMapping("/post")
public class PostController {

	@Resource(name = "postService")
	private PostServiceInf postService;

	@Resource(name = "boardService")
	private BoardServiceInf boardService;

	@Resource(name = "commentaryService")
	private CommentaryServiceInf commentaryService;

	@Resource(name = "attachService")
	private AttachServiceInf attachService;

	/**
	 * Method : postBoardList 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 게시판 번호로 게시글 목록(리스트) 가져오기
	 */

	@RequestMapping("/postBoardList")
	public String postBoardList(Model model, PageVo pageVo, BoardVo boardVo, PostVo postVo, HttpServletRequest request)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		String searchText = "";
		if (request.getParameter("searchText") != null) {
			searchText = request.getParameter("searchText");
		}
		// left.jsp 에서 게시판이름을 클릭했을때 전의 상태로 돌아가서
		// 새로고침 효과를 주는 것.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		Map<String, Object> resultMap = postService.postBoardList(pageVo);

		String userId = postVo.getUserId();
		String board_name = boardVo.getBoard_name();
		String board_no = boardVo.getBoard_no();
		int post_no = postVo.getPost_no();

		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int pageCnt = (int) resultMap.get("pageCnt");

		model.addAllAttributes(resultMap);
		model.addAttribute("userId", userId);
		model.addAttribute("board_name", board_name);
		model.addAttribute("board_no", board_no);
		model.addAttribute("post_no", post_no);

		return "postList";
	}

	/**
	 * Method : postPageListAjax 작성자 : pc07 변경이력 :
	 * 
	 * @param model
	 * @param pageVo
	 * @return Method 설명 : ajax
	 */
	@RequestMapping("/postPageListAjax")
	public String postPageListAjax(Model model, PageVo pageVo, @RequestParam(value = "board_no") int board_no) {

		Map<String, Object> resultMap = postService.postBoardList(pageVo);

		if (pageVo.getSearchText() == null) {
			pageVo.setSearchText("");
		}
		resultMap.put("pageVo", pageVo);
		model.addAllAttributes(resultMap);
		return "jsonView";
	}

	/**
	 * Method : postNew 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 게시글 목록(리스트) 새글작성 버튼 클릭시 jsp 화면 보이기
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/postNew")
	public String postNew(Model model, PostVo postVo, @RequestParam(value = "board_no") int board_no,
			@RequestParam(value = "post_no") int post_no) throws IOException, ServletException {

		// 넘기려고 담아주기
		model.addAttribute("post_no", post_no);
		System.out.println("post_no + postNew 에서 찍어봅니다 : " + post_no);
		model.addAttribute("post_board", board_no);

		// left.jsp 에서 게시판이름을 클릭했을때 전의 상태로 돌아가서
		// 새로고침 효과를 주는 것.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		return "postNew";
	}

	/**
	 * Method : postNewSave 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : post로 보낸값을 저장하고 저장후엔 list화면을 보여준다.
	 * @throws UnsupportedEncodingException
	 * 
	 */
	@RequestMapping(value = "/postNewSave", method = RequestMethod.POST)
	public String postNewSave(PostVo postVo, Model model, @RequestParam("attach_name") List<MultipartFile> parts,
			HttpServletRequest request) throws UnsupportedEncodingException {

		int post_board = postVo.getPost_board();

		int insertpost = postService.insertPostNo(postVo);
		AttachVo attachVo = new AttachVo();

		for (MultipartFile part : parts) {
			String path = "D:\\A_TeachingMaterial\\8.upload";
			String fileName = UUID.randomUUID().toString();
			String originalFileName = part.getOriginalFilename();

			
			if (!(part.getSize() == 0 || part.isEmpty() || part.getOriginalFilename().equals(""))) {

				attachVo.setAttach_name("/files/" + fileName);

				attachVo.setAttach_name(originalFileName);

				String attach_name = attachVo.getAttach_name();

				File file = new File(path + File.separator + fileName);
				try {
					part.transferTo(file);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				int result = attachService.insertFile(attachVo);
			}
		}
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		List<AttachVo> attList = attachService.selectAttachment(attachVo.getAttach_post());
		
		model.addAttribute("attList", attList);
		return "redirect:/post/postBoardList?board_no=" + post_board + "&page=1&pageSize=10";
	}

	/**
	 * Method : postDetail 작성자 : pc07 변경이력 :
	 * 
	 * @param model
	 * @return Method 설명 : 게시글 상세 가져오기
	 */
	@RequestMapping(value = "/postDetail", method = RequestMethod.GET)
	public String postDetail(Model model, @RequestParam(value = "post_no") int post_no) {

		PostVo postVo = postService.selectOnePost(post_no);
		model.addAttribute("postVo", postVo);

		List<CommentaryVo> commList = commentaryService.selectPostComment(post_no);
		model.addAttribute("commList", commList);
		
		//List<AttachVo> selectattach = attachService.selectAttachment(post_no);
		//model.addAttribute("selectattach",selectattach);
		
		// left.jsp 계속 나오게 하는거.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		model.addAttribute("post_no", post_no);
		List<AttachVo> attList  = attachService.selectAttachment(post_no);
		model.addAttribute("attList", attList);
		

		return "postDetail";
	}

	/**
	 * Method : postUpdate 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 게시글 수정--> jsp로 넘겨주기
	 */
	@RequestMapping("/postUpdateView")
	public String postUpdateView(Model model, @RequestParam("post_no") int post_no
			,@RequestParam("post_board") int post_board) {

		PostVo postVo = postService.selectOnePost(post_no);
		List<AttachVo> attList = attachService.selectAttachment(post_no);
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		model.addAttribute("post_no",post_no);
		model.addAttribute("postVo", postVo);
		model.addAttribute("attList",attList);
		
		System.out.println("att" +attList);
		
		return "postUpdate";
	}

	/**
	 * Method : postDelete 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 게시글 수정 Model 객체 값을 넣어줄때 사용한다.
	 */

	@RequestMapping(value = "/postUpdate", method = RequestMethod.POST)
	public String postUpdate(Model model, PostVo postVo,@RequestParam("post_no") int post_no
			,AttachVo attachVo,@RequestParam("attach_name") String attach_name) {
		// update
		int postUpdate = postService.updatePost(postVo);

		// 수정 
		attachVo.setAttach_no(post_no);
		attachVo.setAttach_name(attach_name);
		int attachUpdate = attachService.updateFile(attachVo);
		List<AttachVo> attList = attachService.selectAttachment(post_no);
		
		System.out.println("attachVo.setAttach_name(attach_name)"+ attachVo.getAttach_name());
		
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("attList",attList);
		model.addAttribute("attach_name",attach_name);
		model.addAttribute("boardUserList", boardUserList);

		// 상세화면으로 넘겨준다.
		return "postDetail";
	}

	/**
	 * Method : postDelete 작성자 : pc07 변경이력 :
	 * 
	 * @return GET 방식으로 온다. Method 설명 : 게시글 삭제
	 */
	@RequestMapping("/postDelete")
	public String postDelete(Model model, @RequestParam(value = "post_no") int post_no,
			@RequestParam(value = "post_board") int post_board) {

		int postDelete = postService.deletePost(post_no);

		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		return "redirect:/post/postBoardList?board_no=" + post_board + "&page=1&pageSize=10";
	}

	/**
	 * Method : postSearch 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 검색 기능
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/postSearch", method = RequestMethod.POST)
	public String postSearch(Model model, PageVo pageVo, BoardVo boardVo, PostVo postVo, HttpServletRequest request,
			@RequestParam(value = "searchText") String searchText) throws UnsupportedEncodingException {

		request.setCharacterEncoding("utf-8");
		pageVo.setSearchText(searchText);

		// left.jsp 에서 게시판이름을 클릭했을때 전의 상태로 돌아가서
		// 새로고침 효과를 주는 것.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		Map<String, Object> resultMap = postService.postBoardList(pageVo);

		String userId = postVo.getUserId();
		String board_name = boardVo.getBoard_name();
		String board_no = boardVo.getBoard_no();

		System.out.println("pageVo" + pageVo.getSearchText());
		List<PostVo> postList = (List<PostVo>) resultMap.get("postList");
		int pageCnt = (int) resultMap.get("pageCnt");

		model.addAttribute("postList1", postList);
		model.addAttribute("searchText", searchText);
		model.addAllAttributes(resultMap);
		model.addAttribute("userId", userId);
		model.addAttribute("board_name", board_name);
		model.addAttribute("board_no", board_no);

		return "redirect:/post/postBoardList";
	}

	/**
	 * Method : postComment 작성자 : pc07 변경이력 :
	 * 
	 * @return Method 설명 : 게시글 답글 jsp 로 넘겨주는곳.
	 */
	@RequestMapping(value = "/postCommentView", method = RequestMethod.GET)
	public String postCommentView(Model model, PostVo postVo, @RequestParam(value = "post_pid") int post_pid,
			@RequestParam(value = "post_no") int post_no) {
		model.addAttribute("post_pid", post_pid);
		return "postCommNew";
	}

	/**
	 * Method : postComment 작성자 : pc07 변경이력 :
	 * 
	 * @param commentaryVo
	 * @return Method 설명 : 게시글 답글 생성부분 detail화면으로 간다.
	 */
	@RequestMapping(value = "/postComment", method = RequestMethod.POST)
	public String postComment(PostVo postVo, Model model, @RequestParam(value = "post_no") int post_no,
			@RequestParam(value = "post_pid") int post_pid) {

		int insertcomment = postService.insertPost(postVo);
		// 새로고침 효과
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);

		return "postDetail";
	}
}
