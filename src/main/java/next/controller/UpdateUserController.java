package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import next.model.User;

// /users/update -> 개인정보수정 화면에서 개인정보를 입력하여 수정버튼 누를 시     /users/updateForm -> 개인정보수정 화면
@WebServlet(value = { "/users/update", "/users/updateForm" })
public class UpdateUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserController.class);
    
    // /users/updateForm 개인정보수정화면 호출 시
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//어떤 아이디의 정보를 수정했는지 알기 위해 아이디를 가져온다.
        String userId = req.getParameter("userId");
        //해당 아이디로 데이터베이스에서 유저객체를 가져온다.
        User user = DataBase.findUserById(userId);
        //로그인한 유저와 수정하려고 하는 유저가 같은지 (자신의 정보만 수정하게 하기 위해서)
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        //로그인한 유저와 수정하려고 하는 유저가 같다면 요청객체에 유저정보를 세팅한다.
        req.setAttribute("user", user);
        //개인정보 수정화면으로 이동한다.
        RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(req, resp);
    }

    //users/update 개인정보수정 화면에서 개인정보를 입력하여 수정버튼 누를 시 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	//해당 아이디로 데이터베이스에서 유저객체를 가져온다.
    	User user = DataBase.findUserById(req.getParameter("userId"));
    	//로그인한 유저와 수정하려고 하는 유저가 같은지 (자신의 정보만 수정하게 하기 위해서)
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        //수정하려고 하는 데이터를 담은 유저객체를 생성한다.
        User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        log.debug("Update User : {}", updateUser);
        //유저정보를 업데이트한다.
        user.update(updateUser);
        resp.sendRedirect("/");
    }
}
