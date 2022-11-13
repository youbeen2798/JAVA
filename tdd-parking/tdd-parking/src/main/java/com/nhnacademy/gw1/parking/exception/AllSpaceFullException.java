package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.Car;

public class AllSpaceFullException extends RuntimeException {

  public AllSpaceFullException(Car car){
    super("All space is full : " + car.getCarNum());
  }

}
