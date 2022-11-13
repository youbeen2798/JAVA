package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.exception.SpaceNotEmptyException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

class ParkingSpaceTest {

  private Car car;
  private ParkingSpace parkingSpace;
  private Field field;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    car = mock(Car.class);
    parkingSpace = new ParkingSpace();

    field = parkingSpace.getClass().getDeclaredField("car");
    field.setAccessible(true);
    field.set(parkingSpace, car);
  }

  @Test
  @DisplayName("빈 주차공간에 주차할 때 성공하는지 확인하는 테스트")
  void empty_parkingSpace_enterCar_then_throw_Exception() throws IllegalAccessException, NoSuchFieldException {
    field.set(parkingSpace, null);

    parkingSpace.enter(car);
    assertThat(parkingSpace.getCar()).isNotNull();
  }

  @Test
  @DisplayName("차가 있는 주차공간에 주차가 실패하여 exception 되는지 확인하는 테스트")
  void not_empty_parkingSpace_enterCar_then_throw_Exception(){

    assertThatThrownBy(() ->  parkingSpace.enter(car))
        .isInstanceOf(SpaceNotEmptyException.class)
        .hasMessageContainingAll("This space is not Empty");
  }

  @Test
  @DisplayName("차가 주차구역에서 나갔을 때 차가 없는지 확인하는 테스트")
  void parkingSpace_exitCar_then_Null(){
    parkingSpace.exit();

    assertThat(parkingSpace.getCar()).isNull();
  }

  @Test
  @DisplayName("주차구역에 차가 들어있을 때 비어있지 않다고 하는지 확인하는 테스트")
  void parkingSpace_empty_parkingSpace_then_False(){
    assertThat(parkingSpace.isEmpty()).isFalse();
  }
}