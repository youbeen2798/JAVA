package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

  String execute(HttpServletRequest request, HttpServletResponse response)
      throws Exception;


}
