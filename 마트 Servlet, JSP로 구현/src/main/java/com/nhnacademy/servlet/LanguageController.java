package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LanguageController implements Command {

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

    String lang = request.getParameter("lang");

    HttpSession session = request.getSession();
    session.setAttribute("lang", lang);
    return "";
  }
}
