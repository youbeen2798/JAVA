package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.exception.AllSpaceFullException;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

  public static final int MAX_CAR_SPACE_NUM = 10; //주차 구역 개수
  private List<ParkingSpace> spaces; //주차 구역 리스트

  public ParkingLot() {
    this.spaces = new ArrayList<>(10);
    init();
  }

  public void init() {
    for (int i = 0; i < MAX_CAR_SPACE_NUM; i++) {
      this.spaces.add(new ParkingSpace());
    }
  }

  //들어온 차는 주차할 공간이 있는지 확인 후 가장 가까운 자리에 주차한다.
  public void enter(Car car) {
    ParkingSpace parkingSpace = findSpace(car); //주차할 공간이 있는지 확인
    parkingSpace.enter(car);
  }

  //주차 구역 빈 공간 찾는 메소드
  private ParkingSpace findSpace(Car car) {
    for (int i = 0; i < MAX_CAR_SPACE_NUM; i++) {
      if (spaces.get(i).isEmpty()) {
        return spaces.get(i);
      }
    }
    throw new AllSpaceFullException(car);
  }
}
