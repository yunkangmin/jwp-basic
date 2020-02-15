package next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/logout")
public class LogoutUserServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		
		//리다이렉트를 하는 이유는 새로고침시 입력한 정보가 재요청되므로 같은 작업이나 같은 데이터가 반복되어 처리될 수 있기 때문이다.
        //302 상태 코드와 경로를 브라우저에게 응답으로 보내어 브라우저가 다시 서버로 경로를 재요청하게 한다.
        //브라우저 경로창이 index.html로 바뀌면서 새로고침시 같은 작업을 반복하지 않게 된다.
	    resp.sendRedirect("/");
		
	}

}
