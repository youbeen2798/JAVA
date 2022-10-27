package com.nhnacademy.servlet;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FoodListController implements Command{

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp)
      throws IOException {
    //GET 요청으로 들어올 때
    return "/foodList.jsp";

  }
}
