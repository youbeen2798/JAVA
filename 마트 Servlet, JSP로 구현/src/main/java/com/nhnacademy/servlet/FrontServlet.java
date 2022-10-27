package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet(name = "frontServlet", urlPatterns = "*.do")
public class FrontServlet extends HttpServlet {

  private static final String REDIRECT_PREFIX = "redirect:";
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setCharacterEncoding("utf-8");
    resp.setContentType("text/html");
    resp.setCharacterEncoding("utf-8");

    try{
      Command command = resolveCommand(req.getRequestURI(), req.getMethod());
      String view = command.execute(req, resp);

      if(view.startsWith(REDIRECT_PREFIX)){
        //redirect로 시작하면 redirect 처리
        resp.sendRedirect(view.substring(REDIRECT_PREFIX.length())); //클라이언트한테 다시 일로가!
      }
      else{
        //redirect가 아니면 JSP에게 view 처리를 위임하여 그 결과를 include 시킴
        RequestDispatcher rd = req.getRequestDispatcher(view);
        rd.include(req, resp); //내가 다시 갈게
      }
    }catch(Exception ex){
      //에러가 발생한 경우는 error page로 지정된 '/error.jsp'에게 view 처리를 위임.
      log.error("", ex);
      req.setAttribute("exception", ex);
      RequestDispatcher rd = req.getRequestDispatcher("/error.jsp");
      rd.forward(req, resp);
    }

  }


  private Command resolveCommand(String servletPath, String method){
    Command command = null;

    if("/cart.do".equals(servletPath)){
      command = new CartController(); //장바구니 링크로 넘어감
    }
    else if("/foodList.do".equals(servletPath) && "GET".equalsIgnoreCase(method)){
      command = new FoodListController(); //상품목록 링크로 넘어감
    }
    else if("/".equals(servletPath) && "POST".equalsIgnoreCase(method)){
      command = new CartController();
    }
    else if("/foods.do".equals(servletPath) && "POST".equalsIgnoreCase(method)){
      command = new CartController();
    }
    else if("/login.do".equals(servletPath)){
      command = new Controller();
    }
    else if("/logout.do".equals(servletPath)){
      command = new LogoutController();
    }
    else if("/language.do".equals(servletPath)){
      command = new LanguageController();
    }
    else if("/pay.do".equals(servletPath)){
      command = new PayController();
    }
    return command;


  }
}
