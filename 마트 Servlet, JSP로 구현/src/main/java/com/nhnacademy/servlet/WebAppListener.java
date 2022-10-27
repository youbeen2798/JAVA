package com.nhnacademy.servlet;

import com.nhnacademy.domain.Food;
import com.nhnacademy.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebAppListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    ServletContext servletContext = sce.getServletContext();

    List<Food> foodList = new ArrayList<>();

    foodList.add(new Food("apple", 20,2000));
    foodList.add(new Food("onion", 2, 1000));
    foodList.add(new Food("egg", 5,1000));
    foodList.add(new Food("green-onion", 10,500));
    servletContext.setAttribute("foodList", foodList);

    //아이디와 비번이 맞는지 확인하기 위한 용도
    servletContext.setAttribute("user", new User("1234", "1234", 20000));

  }
}
