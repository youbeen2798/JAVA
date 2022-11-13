package com.nhnacademy.gw1.parking.exception;

import com.nhnacademy.gw1.parking.User;

public class UserMoneyNotEnoughException extends RuntimeException{
  public UserMoneyNotEnoughException(User user, long parkingFee){
    super("User ( " + user.getUserId() + " ) money is Not Enough : "
    + "ParkingFee is " + parkingFee);
  }
}

