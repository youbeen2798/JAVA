package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.exception.SpaceNotEmptyException;
import lombok.Getter;

@Getter
public class ParkingSpace {

  private Car car;
  static int num = 0;

  //주차구역에 차가 들어옴
  public void enter(Car car){
    if(isEmpty()){
      this.car = car;
    }
    else{
      throw new SpaceNotEmptyException(car);
    }
  }

  //주차구역에 차가 나감
  public void exit(){
    this.car = null;
  }

  //주차구역에 차가 있는지 확인 -> boolean
  public boolean isEmpty(){
    return this.car == null;
  }
}
