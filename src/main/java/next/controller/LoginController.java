package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import next.model.User;

///users/login -> 로그인 버튼을 누를 시 작동, /users/loginForm -> 로그인 입력화면으로 이동
@WebServlet(value = { "/users/login", "/users/loginForm" })
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // /user/loginForm 경로로 요청 시 
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        forward("/user/login.jsp", req, resp);
    }
    
    // /users/login 경로로 요청 시
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//로그인 시 입력한 아이디와 비밀번호를 가져온다.
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        //데이터베이스 객체에서 로그인 시 입력한 아이디에 해당하는 유저 객체가 있으면 가져온다.
        User user = DataBase.findUserById(userId);
        //유저 객체가 없다면 로그인 실패
        if (user == null) {
        	// 로그인 페이지에서 "아이디 또는 비밀번호가 틀렷다"는 문구를 띄워주기 위해 데이터 세팅
        	req.setAttribute("loginFailed", true);
            forward("/user/login.jsp", req, resp);
            return;
        }
        
        //아이디는 확인이 됬고 비밀번호가 맞다면
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            //세션에 "user"라는 문자열을 키로 세션에 유저객체를 세팅한다.
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            resp.sendRedirect("/");
        } else {//비밀번호가 틀리면        
            req.setAttribute("loginFailed", true);
            forward("/user/login.jsp", req, resp);
        }
    }
    
    //화면을 이동하는 메서드
    private void forward(String forwardUrl, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher(forwardUrl);
        rd.forward(req, resp);
    }
}
