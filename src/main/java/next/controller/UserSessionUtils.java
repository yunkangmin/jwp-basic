package next.controller;

import javax.servlet.http.HttpSession;

import next.model.User;

public class UserSessionUtils {
    public static final String USER_SESSION_KEY = "user";
    
    //세션에 유저정보가 있는지 확인하는 메서드
    //유저정보가 있다면 유저정보를 반환
    //없다면 null을 반환
    public static User getUserFromSession(HttpSession session) {
        Object user = session.getAttribute(USER_SESSION_KEY);
        if (user == null) {
            return null;
        }
        return (User) user;
    }

    //로그인을 한 상태인지 확인하는 메서드
    public static boolean isLogined(HttpSession session) {
        if (getUserFromSession(session) == null) {
            return false;
        }
        return true;
    }
    
    //로그인 한 유저와 수정하려고 하는 유저가 같은지 (자신의 정보만 수정하기 위해서)
    public static boolean isSameUser(HttpSession session, User user) {
    	//로그인이 안되어 있다면
        if (!isLogined(session)) {
            return false;
        }
        
        //유저정보가 없다면
        if (user == null) {
            return false;
        }
        
        //로그인한 유저와 매개변수로 온 user객체가 같다면 true
        return user.isSameUser(getUserFromSession(session));
    }
}
