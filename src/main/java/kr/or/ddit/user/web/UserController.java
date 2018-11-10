package kr.or.ddit.user.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceInf;

@RequestMapping("/user")
@Controller
public class UserController {
	
	@Resource(name="boardService")
	private BoardServiceInf boardService;
	
	// @Resource 어노테이션을 통해 userService 를 주입 
	@Resource(name="userService")
	private UserServiceInf userservice;
	
	/**
	 * Method : loginView
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 로그인 화면을 보여주기 위해 
	 */
	@RequestMapping("/loginView")
	public String loginView() {
		return "login/login";		
	}	
	
	
	/**
	 * Method : loginProcess
	 * 작성자 : pc07
	 * 변경이력 :
	 * @param request
	 * @param userVo
	 * @return
	 * Method 설명 : 로그인 화면에서 DB에있는 값과 
	 *               jsp에서 파라미터로 받아오는 값을 비교하는 곳.
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/loginProcess" , method=RequestMethod.POST)
	public String loginProcess(HttpServletRequest request , Model model) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("utf-8");
		
		// login.jsp 에서 name값으로 준 userId와 pass를 파라미터로 받아서 저장.
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		
		// 로그인시 아이디 값을 받아올수있는 쿼리를 불러온다. 
	    UserVo userVo = userservice.selectUser(userId);
	    
	    // 로그인시 DB값과 같은지 if문을 통해 확인한다. null 검사도 같이 
		if(userVo==null || !userId.equals(userVo.getUserId())||!pass.equals(userVo.getPass())) {
			return "login/login";
		}else{
			// main.jsp에서 쓰려고 model객체에 저장해준다.!
			//model.addAttribute("userId",userId);
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			// return 다음엔 viewname을 쓰면된다
			List<BoardVo> boardUserList = boardService.boardUserList();
			request.getServletContext().setAttribute("boardUserList", boardUserList);
			
			return "main";
		}
	}	
	
	/**
	 * Method : logout
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : header부분에서 logout 버튼을 클릭하면
	 * 				 login.jsp로 가게 했음. 
	 */
	@RequestMapping("/logout")
	public String logout() {
		return "login/login";
	}
	
}
























