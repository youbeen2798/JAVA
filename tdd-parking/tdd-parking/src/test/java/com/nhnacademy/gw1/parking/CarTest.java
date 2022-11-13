package com.nhnacademy.gw1.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarTest {

  private Car car;
  private User user;
  private Field field;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    this.user = mock(User.class);
    this.car = new Car(1,user);

    field = car.getClass().getDeclaredField("user");
    field.setAccessible(true);
    field.set(car, user);
  }

  @Test
  @DisplayName("차 고유 번호 성공 테스트")
  void car_getCarNum() {
    assertThat(this.car.getCarNum()).isEqualTo(1);
  }

  @Test
  @DisplayName("자동차 소유주가 가지고 있는 돈 일치 성공 테스트")
  void car_getUserMoney(){
    when(user.getAmount()).thenReturn(10000L);
    assertThat(this.car.getUserMoney()).isEqualTo(user.getAmount());
  }

  @Test
  @DisplayName("자동차 타입이 경차일 때 경차를 인식하는지 테스트")
  void car_checkCarType_LIGHT_CAR(){
    this.car.setCarType(CarType.LIGNT_CAR);
    assertThat(car.isLightCar()).isTrue();
  }

  @Test
  @DisplayName("자동차 타입이 중형차일 때 경차가 아님을 인식하는지 테스트")
  void car_checkCarType_MIDSIZE_CAR(){
    this.car.setCarType(CarType.MIDSIZE_CAR);
    assertThat(car.isLightCar()).isFalse();
  }

  @Test
  @DisplayName("자동차 타입이 대형차일 때 경차가 아님을 인식하는지 테스트")
  void car_checkCarType_LARGE_CAR(){
    this.car.setCarType(CarType.LARGE_CAR);
    assertThat(car.isLightCar()).isFalse();
  }
}