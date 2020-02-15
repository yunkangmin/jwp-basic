package next.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

//회원가입 후 나타나는 유저목록 페이지
@WebServlet("/user/list") 
public class ListUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ListUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("users", DataBase.findAll());
        //logger.debug("users {}", DataBase.findAll());
        
        HttpSession session = req.getSession();
        //세션에 user라는 이름으로 된 밸류값이 있으면 user객체가 나온다.
        Object value = session.getAttribute("user");
        RequestDispatcher rd;
        //유저정보가 있다면 -> 현재 로그인 중
        if (value != null) {
        	req.setAttribute("users", DataBase.findAll());
        	rd = req.getRequestDispatcher("/user/list.jsp");
        }else {//로그인 중이 아니라면
        	rd = req.getRequestDispatcher("/");
        }
        rd.forward(req, resp);
    }
}
