package com.nhnacademy.gw1.parking;

import java.time.LocalDateTime;

public interface PaySystem {

  boolean checkUserMoneyEnough(User carUser, long parkingFee);

  long measureParkingFee(LocalDateTime enterTime, LocalDateTime exitTime);

  long measureParkingFee_over24(LocalDateTime enterTime, LocalDateTime exitTime);

  long measureParkingFee_under24(LocalDateTime enterTime, LocalDateTime exitTime);

  long measureEnterDayParkingFee(LocalDateTime enterTime);

  long measureExitDayParkingFee(LocalDateTime exitTime);

  long measureFeeBySeconds(long seconds);

  boolean under30minutes(long seconds);

  public boolean pay(Long carNum, LocalDateTime enterTime, LocalDateTime exitTime, User carUser);

  }
