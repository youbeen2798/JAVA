package com.nhnacademy.servlet;

import com.nhnacademy.domain.Food;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.jscience.physics.amount.AmountException;

@Slf4j
public class CartController implements Command {

  //GET - 응답에 장바구니에 담긴 상품 목록과 전체 금액 표시
  //POST - 장바구니에 담기
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    //로그인한 세션을 가져옴
    HttpSession session = req.getSession(false);

    //장바구니 객체가 없다면 세션에 생성 후 wishlist 맵에 저장
    if (session.getAttribute("wishlist") == null) {
      session.setAttribute("wishlist", new HashMap<String, Integer>());
    }
    Map<Food, Integer> wishlist = (Map<Food, Integer>) session.getAttribute("wishlist");

    //GET 요청일 때- 응답에 장바구니에 담긴 상품 목록과 전체 금액 표시해주는 페이지로 넘어감
      if (req.getMethod().equals("GET")) {
        int totalWishListPrice = 0; //전체 금액
        for(Entry<Food, Integer> entrySet: wishlist.entrySet()){
            Food food = entrySet.getKey();
            int foodPrice = food.getPrice(); //음식 가격
            int foodCnt = entrySet.getValue(); //음식 개수
            totalWishListPrice += foodPrice * foodCnt;
        }
        req.getSession().setAttribute("totalWishListPrice", totalWishListPrice);
        return "/cart.jsp";
      }
      //POST - 장바구니에 담기
      else if (req.getMethod().equals("POST")) {
        String orderedFoodName = req.getParameter("fruits");
        int orderedFoodCnt = Integer.parseInt(req.getParameter("foodCnt"));

       ServletContext servletContext =  req.getServletContext();
        //장바구니에 추가하기
        List<Food> foodList = (List<Food>) servletContext.getAttribute("foodList");
        for(Food food: foodList){
          if(food.getName().equals(orderedFoodName)){
            //만약 상품수량보다 더 많은 주문을 하지 않았다면
            if(food.getCount() >= orderedFoodCnt){
              int foodCnt = food.getCount() - orderedFoodCnt;
              //상품 매대 음식 개수 줄이기
              food.setCount(foodCnt);
              //장바구니에 상품 집어넣기
              wishlist.put(food, wishlist.getOrDefault(food, 0) + orderedFoodCnt);
            }  //상품 수량 부족시
            else{
              throw new AmountException();
            }
          }
        }
        Iterator<Food> iter = wishlist.keySet().iterator();

        //상품 매대 과일 갱신
        servletContext.setAttribute("foodList", foodList);

        //장바구니 갱신
        session.setAttribute("wishlist", wishlist);

        return "redirect:/cart.do";
      }
      return "redirect:/index.jsp";
    }
  }

