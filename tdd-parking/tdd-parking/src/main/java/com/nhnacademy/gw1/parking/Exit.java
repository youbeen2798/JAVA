package com.nhnacademy.gw1.parking;

import java.time.LocalDateTime;

public class Exit {
  //출차한 시간 리턴
  public LocalDateTime exit(Car car){
    return LocalDateTime.now();
  }
}
