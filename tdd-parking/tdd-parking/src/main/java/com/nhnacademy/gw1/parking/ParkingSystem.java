package com.nhnacademy.gw1.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

//이것이 stub이 될 것임
public class ParkingSystem {
  //입출시간과 출차시간(NOW)을 확인할 입구와 출구

  private final List<Entrance> entranceList;
  private final List<Exit> exitList;
  private final PaySystem paySystem;
  Map<Long, LocalDateTime> enterMap;
  Map<Long, LocalDateTime> exitMap;
  Map<Long, User> userMap;

  public ParkingSystem(List<Entrance> entranceList, List<Exit> exitList){
    this.entranceList =  new ArrayList<>(10); //입구가 10개
    this.exitList = new ArrayList<>(10); //출구가 10개라 가정
    this.paySystem = new DefaultPaySystem();
    this.enterMap = new ConcurrentHashMap<>();
    this.exitMap = new ConcurrentHashMap<>();
  }

  public void enter(Car car){
    //주차장 입구 10개 중 랜덤 입구로 들어간다 가정 => 랜덤은 n번 반복 시 1/n 확률로 입구를 이용 가능
    LocalDateTime enterTime = entranceList.get(new Random().nextInt(10)).enter(car);
    userMap.put(car.getCarNum(), car.getUser());
    enterMap.put(car.getCarNum(), enterTime);
  }

  public void exit(Car car){
    //주차장 출구 10개 중 랜덤 출구로 나간다 가정 => 랜덤은 n번 반복 시 1/n 확률로 출구를 이용 가능
    LocalDateTime exitTime = exitList.get(new Random().nextInt(10)).exit(car);
    exitMap.put(car.getCarNum(), exitTime);
    pay(car);
  }

  //차의 고유번호와, 입출차시간을 결제 시스템에 위임하는 메소드
  public void pay(Car car) {
    Long carNum = car.getCarNum(); //자동차 번호
    LocalDateTime enterTime = enterMap.get(carNum); //입차 시간
    LocalDateTime exitTime = exitMap.get(carNum); //출차 시간
    User carUser = car.getUser(); //자동차 소유주
    this.paySystem.pay(carNum, enterTime, exitTime, carUser);
  }
}
