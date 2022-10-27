package com.nhnacademy.servlet;

import com.nhnacademy.domain.Food;
import com.nhnacademy.domain.User;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

//결제하는 클래스
@Slf4j
public class PayController implements Command {

  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp)
      throws Exception {
    if (req.getMethod().equals("GET")) {
      return "pay.jsp";

    } else if (req.getMethod().equals("POST")) {
      //체크된 결제할 상품들
      String checkedFood[] = req.getParameterValues("willpayfood");

      HttpSession session = req.getSession();

      //나의 장바구니
      Map<Food, Integer> wishlist = (Map<Food, Integer>) session.getAttribute("wishlist");

      int payPrice = 0;

      Iterator<Food> iter = wishlist.keySet().iterator();

      //장바구니를 돌면서 각 체크한 품목들의 수만큼 가격을 체크한다.
      while (iter.hasNext()) {
        Food food = iter.next();
        String foodName = food.getName();
        for (int i = 0; i < checkedFood.length; i++) {
          if (checkedFood[i].equals(foodName)) {
            int foodCnt = wishlist.get(food);
            payPrice += foodCnt * food.getPrice();
            break;
          }
        }
      }

      User user = (User) session.getAttribute("user");
      int havingMoney = user.getMoney(); //사용자가 가지고 있는돈

      //만약 결제금액보다 가지고 있는 돈이 크다면
      if (payPrice < havingMoney) {
        session.setAttribute("user", new User(user.getId(), user.getPw(), havingMoney - payPrice));
        log.info("hi");
        iter = wishlist.keySet().iterator();

        //장바구니에서 매대 상품 제거
        while (iter.hasNext()) { //위시리스트
          Food food = iter.next(); //위시리스트
          String foodName = food.getName();
          for (int i = 0; i < checkedFood.length; i++) {
            if (checkedFood[i].equals(foodName)) {
              wishlist.remove(food);
              break;
            }
          }
        }
        return "redirect:/index.jsp"; //원래 화면으로
      } else { //잔액 부족시
        throw new NotEnoughMoneyException("잔액이 부족합니다");
      }
    }
    return "redirect:/pay.jsp";
  }

  class NotEnoughMoneyException extends Exception{
    NotEnoughMoneyException(String msg){
      super(msg);
    }
  }

}

