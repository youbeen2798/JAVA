package com.nhnacademy.gw1.parking;

import java.time.LocalDateTime;

public class Entrance {
  ParkingLot parkingLot;

  public Entrance(){
    this.parkingLot = new ParkingLot();
  }

  //입차시간 리턴
  public LocalDateTime enter(Car car){
    parkingLot.enter(car);
    return LocalDateTime.now();
  }
}
