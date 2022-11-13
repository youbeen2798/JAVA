package com.nhnacademy.gw1.parking;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class UserTest {
  private User user1;
  private User user2;

  @BeforeAll
  void setUp(){
    user1 = new User(10000);
    user2 = new User(20000);
  }

  @Test
  @DisplayName("사용자를 식별할 고유 ID 확인 테스트")
  @Order(1)
  void getUserId() {
    //첫 번째 사용자의 userId가 1, 두번째 사용자의 userId가 2인지 확인
    assertAll("userIdtest",
        () -> assertEquals(1, user1.getUserId()),
        () -> assertEquals(2, user2.getUserId()));
  }

  @Test
  @DisplayName("각 자동차 소유주의 돈의 양을 확인하는 테스트")
  @Order(2)
  void getAmount() {
    //첫 번째 사용자가 가지고 있는 돈은 10000, 두번째 사용자의 가지고 있는 돈은 20000인지 확인
    assertAll("userAmounttest",
        () -> assertEquals(10000, user1.getAmount()),
        () -> assertEquals(20000, user2.getAmount()));
  }
}