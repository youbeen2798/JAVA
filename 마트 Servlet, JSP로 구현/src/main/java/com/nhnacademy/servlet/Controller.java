package com.nhnacademy.servlet;

import com.nhnacademy.domain.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller implements Command{

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    //loginForm.html에서 POST요청을 받으면
    if(req.getMethod().equals("GET")) {
      return "/loginForm.jsp";
    }
    else if(req.getMethod().equals("POST")){
      User user = (User) req.getServletContext().getAttribute("user");
      String id = user.getId();
      String pw = user.getPw();
      String inputId = req.getParameter("id");
      String inputPw = req.getParameter("password");

      if(id.equals(inputId) && pw.equals(inputPw)){
        HttpSession session = req.getSession();
        session.setAttribute("id", inputId);

        session.setAttribute("user", new User(id, pw, 20000));

        return "redirect:/";  //jsp한테 위임하는 것
      }
      else{
        return "/loginForm.jsp";
      }
   }
    return null;
  }

}
