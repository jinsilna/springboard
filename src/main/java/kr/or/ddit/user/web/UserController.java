package kr.or.ddit.user.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.naver.naverlogintutorial.oauth.bo.NaverLoginBO;

import kr.or.ddit.board.model.BoardVo;
import kr.or.ddit.board.service.BoardServiceInf;
import kr.or.ddit.user.model.UserVo;
import kr.or.ddit.user.service.UserServiceInf;

@RequestMapping("/user")
@Controller
public class UserController {
	
    /* NaverLoginBO */
    private NaverLoginBO naverLoginBO;
    private String apiResult = null;
    
    @Autowired
    private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
        this.naverLoginBO = naverLoginBO;
    }
	
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
	public String loginView(HttpServletRequest request , Model model, HttpSession session) {
		
        /* 네이버아이디로 인증 URL을 생성하기 위하여 naverLoginBO클래스의 getAuthorizationUrl메소드 호출 */
        String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
        
        //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=sE***************&
        //redirect_uri=http%3A%2F%2F211.63.89.90%3A8090%2Flogin_project%2Fcallback&state=e68c269c-5ba9-4c31-85da-54c16c658125
        System.out.println("네이버:" + naverAuthUrl);
        
        //네이버 
        model.addAttribute("url", naverAuthUrl);
        
		return "login/login";		
	}	
	
	/**
	 * Method : main
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : main 화면 
	 */
	@RequestMapping("/main")
	public String main(HttpServletRequest request) {
		// return 다음엔 viewname을 쓰면된다
		
		List<BoardVo> boardUserList = boardService.boardUserList();
		request.getServletContext().setAttribute("boardUserList", boardUserList);
		
		return "main";
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
			session.setAttribute("userVo", userVo);
			
			// return 다음엔 viewname을 쓰면된다
			List<BoardVo> boardUserList = boardService.boardUserList();
			request.getServletContext().setAttribute("boardUserList", boardUserList);
			
		
			return "main";
		}
	}	
	
	
	
	/**
	 * Method : SignView
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 회원가입 화면
	 */
	@RequestMapping("/signView")
	public String signView() {
		return "login/signUp";
	}
	
	/**
	 * Method : signProcess
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : 회원가입 버튼을 클릭하고 나서의 
	 */
	@RequestMapping("/signProcess")
	public String signProcess(UserVo userVo) {
		
		/*
		 UserVo userVo 로 받아 오려고 했는데 안됬다.
		 이유는? 다른값들은 null이있어서 안되는건가
		 그런거같다.
		 */
		// 아이디
		String userId = userVo.getUserId();
		userVo.setUserId(userId);
		
		// 이름
		String name = userVo.getName();
		userVo.setName(name);
		
		// 비밀번호
		String pass = userVo.getPass();
		userVo.setPass(pass);
		
		// 핸드폰 번호 
		String tel = userVo.getTel();
		userVo.setTel(tel);
	
		int insertUser = userservice.insertUser(userVo);
		//로그인 화면으로 돌아간다.
		
		return "login/login";
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
	
	
	/**
	 * Method : callback
	 * 작성자 : pc07
	 * 변경이력 :
	 * @return
	 * Method 설명 : naverAPI를 위한
 	 *  			 callback	
 	 */
	/*@RequestMapping("/callback")
	public String callback() {
		return "login/callback";
	}*/
	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public String callback(Model model, @RequestParam String code, @RequestParam String state, HttpSession session)
			throws IOException {
		System.out.println("여기는 callback");
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		//로그인 사용자 정보를 읽어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		System.out.println(naverLoginBO.getUserProfile(oauthToken).toString());
		model.addAttribute("result", apiResult); 
		System.out.println("result"+apiResult);


		/* 네이버 로그인 성공 페이지 View 호출 */
		//	      JSONObject jsonobj = jsonparse.stringToJson(apiResult, "response");
		//	      String snsId = jsonparse.JsonToString(jsonobj, "id");
		//	      String name = jsonparse.JsonToString(jsonobj, "name");
		//	      UserVO vo = new UserVO();
		//	      vo.setUser_snsId(snsId);
		//	      vo.setUser_name(name);
		//	      System.out.println(name);
		//	      try {
		//	          vo = service.naverLogin(vo);
		//	      } catch (Exception e) {
		//	          // TODO Auto-generated catch block
		//	          e.printStackTrace();
		//	      }


		//	      session.setAttribute("login",vo);
		//	      return new ModelAndView("user/loginPost", "result", vo);
	 
	    	return "login/callback";
	 }
}
























