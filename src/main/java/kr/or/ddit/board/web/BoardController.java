package kr.or.ddit.board.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	/**
	 * Method : boardList
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판 전체 가져오기 / 게시판 사용여부 체크 
	 * 나의 생각 : 같은 화면에서 처리되는거라 이곳에서 두개를 같이 처리하면 될거같다.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/List")
	public String boardList(Model model,HttpServletRequest request ) throws UnsupportedEncodingException {
	
		request.setCharacterEncoding("utf-8");
		
		// 전체 게시판 정보를 가져온다.
		List<BoardVo> boardList = boardService.boardList();
		// 매개변수로 선언해준 model 객체를 통해 다음화면에 나올 수 있도록 attribute해준다.
		model.addAttribute("boardList",boardList);
		
		// 전체 게시판에서 사용이 'Y' 인걸 가져온다. left.jsp를 위함.
		List<BoardVo> boardUserList = boardService.boardUserList();
		model.addAttribute("boardUserList", boardUserList);
		
		return "board";
	}
	
	/**
	 * Method : boardCreate
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 게시판을 생성하고 수정하기부분을 처리하기 위한 메소드
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/CreateUpdate" , method=RequestMethod.POST)
	public String boardCreate(HttpServletRequest request , Model model, BoardVo boardVo) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		
		// BoardVo에 값이 담겨있기 때문에 10줄코드를 간추릴수 있다.
		String board_no = boardVo.getBoard_no();

		// 게시판 생성 부분
		// 게시판 아이디가 DB에있는 게시판 아이디랑 다르면 insert
		if(board_no.equals("")){
			int insertboard = boardService.insertBoardList(boardVo);
			
			// 새로고침 효과 
			List<BoardVo> boardUserList = boardService.boardUserList();
			model.addAttribute("boardUserList", boardUserList);
			
		}else {
			//게시판 수정 부분 
			int updateboard = boardService.updateBoard(boardVo);
			
			// 새로고침 효과 
			List<BoardVo> boardUserList = boardService.boardUserList();
			model.addAttribute("boardUserList", boardUserList);
		}
		// 새로고침 효과 
		List<BoardVo> boardList = boardService.boardList();
		model.addAttribute("boardList", boardList);
		return "board";
	}
	
	
}
