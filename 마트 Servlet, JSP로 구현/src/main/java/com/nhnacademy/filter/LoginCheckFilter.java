package com.nhnacademy.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//로그인 안했으면 로그인해야함
//로그인을 안해도 로그인 페이지는 갈 수 있어야 함
@WebFilter(filterName="loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

  private Set<String> excludedUrls = new HashSet<>();

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String urls = filterConfig.getInitParameter("exclude-urls");
    excludedUrls = Arrays.stream(urls.split("\n")).map(String::trim).collect(Collectors.toSet());
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    HttpSession session = ((HttpServletRequest) request).getSession();

    Object id = session.getAttribute("id");

    if(Objects.isNull(id)){
      if(excludedUrls.contains(((HttpServletRequest)request).getRequestURI())){
        //로그인이 필요없으면
        chain.doFilter(request, response);
        return;
      }
      //세션이 없는데 로그인이 필요하면
      ((HttpServletResponse)response).sendRedirect("/login.do");
    }
    else{ //세션이 있으면(이미 로그인이 되있으면 그냥 지나가야함)
      chain.doFilter(request, response);
    }
  }

}
