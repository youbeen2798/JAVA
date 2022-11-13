package com.nhnacademy.gw1.parking.exception;

public class InvalidTimeException extends RuntimeException{

  public InvalidTimeException(Long carNum){
    super("Cannot measure between EnterTime and ExitTime : " + carNum);
  }
}

