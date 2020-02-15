package next.web;

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

//회원가입 후 나오는 list.jsp에서 수정버튼을 클릭할 시 해당 아이디로 사용자 정보를 가져와서 요청객체에 세팅한다.
@WebServlet("/user/form")
public class UpdateUserFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(UpdateUserFormServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	log.debug("id확인 {} " , req.getParameter("userId"));
    	//아이디로 데이터베이스에서 유저객체를 가져와 요청객체에 저장한다.
        req.setAttribute("user", DataBase.findUserById(req.getParameter("userId")));
        RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
        rd.forward(req, resp);
    }
}
