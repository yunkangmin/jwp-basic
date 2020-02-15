package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User; 

//로그인 시
@WebServlet("/user/login") 
public class LoginUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//파라미터에서 아이디를 가져온다.
		String userId = req.getParameter("userId");
		User user;
		//아이디로 된 유저정보가 없다면
		if((user = DataBase.findUserById(userId)) == null) {
			resp.sendRedirect("/user/login_failed.jsp");
			return;
		}
		
		String url;
		String password = req.getParameter("password");
		//아이디로 검색한 유저정보에서 추출한 패스워드와 파라미터로 넘어온 패스워드가 같다면
		if(user.getPassword().equals(password)) {
			//세션을 구한다.
			HttpSession session = req.getSession();
			//세션에 user이름으로된 유저객체를 저장한다.
			session.setAttribute("user", user);
			url = "/";
		}else {
			url = "/user/login_failed.jsp";
		}
		//리다이렉트를 하는 이유는 새로고침시 입력한 정보가 재요청되므로 같은 작업이나 같은 데이터가 반복되어 처리될 수 있기 때문이다.
        //302 상태 코드와 경로를 브라우저에게 응답으로 보내어 브라우저가 다시 서버로 경로를 재요청하게 한다.
        //브라우저 경로창이 index.html로 바뀌면서 새로고침시 같은 작업을 반복하지 않게 된다.
	    resp.sendRedirect(url);
		
	}

}
