<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file = "include.jsp" %>
<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/user/update">
            <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <!-- input태그에서 입력을 막는 방법은 readonly와 disabled가 있다. 다만, disabled는 form태그 안에서 사용 시 데이터가 전달되지 않는다. -->
                  <input class="form-control" id="userId" name="userId" placeholder="User ID" value="${user.userId}" readonly>
                  <input type="hidden" name="userId" value="${user.userId}" />
              </div>
              <div class="form-group">
                  <label for="password">비밀번호</label>
                  <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="${user.password}">
              </div>
              <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name" placeholder="Name" value="${user.name}">
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="Email" value="${user.email}">
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">수정</button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

<!-- script references -->
<script src="../js/jquery-2.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/scripts.js"></script>
	</body>
</html>