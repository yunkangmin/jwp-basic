package next.controller;

import core.db.DataBase;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//사용자 목록보기 클릭 시
@WebServlet("/users")
public class ListUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    //
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//로그인이 되어 있지 않다면
        if (!UserSessionUtils.isLogined(req.getSession())) {
        	//로그인 페이지로 이동
            resp.sendRedirect("/users/loginForm");
            return;
        }
        //로그인이 되어 있다면 데이터베이스에 저장된 모든 유저정보를 가져와서 요청객체 세팅한다.
        req.setAttribute("users", DataBase.findAll());

        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
