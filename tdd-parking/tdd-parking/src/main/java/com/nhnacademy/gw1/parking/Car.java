package com.nhnacademy.gw1.parking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Car {

  private int carNum; //차 번호
  private User user; //차 소유주
  private CarType carType; //자동차 타입(경차, 중형차, 대형차)

  public Car(int carNum, User user){
    this.carNum = carNum;
    this.user = new User(10000); //10000원 소유
  }

  public long getCarNum(){
    return this.carNum;
  }

  public long getUserMoney(){
    return this.user.getAmount();
  }

  public void setCarType(CarType carType){
      this.carType = carType;
  }

  public boolean isLightCar(){
    return this.carType == CarType.LIGNT_CAR;
  }
}
