package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.Car;

public class SpaceNotEmptyException extends RuntimeException{

  public SpaceNotEmptyException(Car car){
    super("This space is not Empty : " + car.getCarNum());
  }
}
