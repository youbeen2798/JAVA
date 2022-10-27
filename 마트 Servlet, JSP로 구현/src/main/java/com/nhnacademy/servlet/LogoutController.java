package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Command{


  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    HttpSession session = request.getSession(false);

    //세션이 없으면
    if(session == null){
      return "redirect:/login.html";
    }
    else{ //세션이 있으면
      session.invalidate();
      return "redirect:/";  //jsp한테 위임하는 것
    }
  }
}
