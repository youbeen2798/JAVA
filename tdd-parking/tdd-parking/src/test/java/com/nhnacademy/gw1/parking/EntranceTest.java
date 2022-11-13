package com.nhnacademy.gw1.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EntranceTest {
  private ParkingLot parkingLot;
  private Entrance entrance;
  private Car car;

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    this.parkingLot = mock(ParkingLot.class);
    this.entrance = new Entrance();
    this.car = mock(Car.class);

    Field field = entrance.getClass().getDeclaredField("parkingLot");
    field.setAccessible(true);
    field.set(entrance, parkingLot);
  }

  @Test
  @DisplayName("차가 입차 후 주차장에 주차되는 로직 성공 테스트")
  void entrance_enter_then_parkingLot_success() {
    entrance.enter(car);

    verify(parkingLot).enter(car);
  }

  @Test
  @DisplayName("차의 입차 시간이 잘 리턴되는지 테스트")
  void entrance_enter_then_returnLocalDateTime_success() {
    assertThat(entrance.enter(car)).isInstanceOf(LocalDateTime.class);
  }
}