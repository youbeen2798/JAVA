package com.nhnacademy.gw1.parking;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

import com.nhnacademy.gw1.parking.exception.AllSpaceFullException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParkingLotTest {

  private Car car;
  private ParkingLot parkingLot;
  private List<ParkingSpace> parkingSpaceList; //주차구역 리스트

  @BeforeEach
  void setUp() throws NoSuchFieldException, IllegalAccessException {
    car = mock(Car.class);
    parkingLot = new ParkingLot();

    parkingSpaceList = new ArrayList<>(10);
    for(int i = 0; i < 10; i++){
      parkingSpaceList.add(mock(ParkingSpace.class));
    }

    Field field = parkingLot.getClass().getDeclaredField("spaces");
    field.setAccessible(true);
    field.set(parkingLot, parkingSpaceList);
  }

  @Test
  @DisplayName("빈 주차장에 차가 들어와 성공적으로 주차하는지 테스트")
  void parkingLot_enter_success(){

    //주차장의 모든 자리가 비어있다고 가정
    parkingSpaceList.forEach(parkingSpace-> when(parkingSpace.isEmpty()).thenReturn(true));

    parkingLot.enter(car);
    verify(parkingSpaceList.get(0)).enter(car);
  }


  @Test
  @DisplayName("차가 가득차있는 주차장에 차가 들어왔을 때 exception 테스트")
  void parkingLot_findSpace_fail_noSpace(){

    //주차장에 차가 다 쌓여있다고 가정
    parkingSpaceList.forEach(parkingSpace-> when(parkingSpace.isEmpty()).thenReturn(false));

    assertThatThrownBy(() -> parkingLot.enter(car))
        .isInstanceOf(AllSpaceFullException.class)
            .hasMessageContainingAll("All space is full");
  }
}
