package com.nhnacademy.gw1.parking;

import com.nhnacademy.gw1.parking.exception.InvalidTimeException;
import com.nhnacademy.gw1.parking.exception.UserMoneyNotEnoughException;
import java.time.Duration;
import java.time.LocalDateTime;

public class ChangedPaySystem implements PaySystem{

  private static final long FIRST_1HOUR_FEE = 1000; ////최초 30분 요금
  private static final long ADDFEE_PER_10MINUTE = 500; //추가 10분당 요금
  private static final long ONE_DAY_MAX_PARKINGFEE = 15000; //일일 주차 최대 요금

  public boolean pay(Long carNum, LocalDateTime enterTime, LocalDateTime exitTime, User carUser) {
    long parkingFee;
    if(enterTime.isBefore(exitTime)){ //만약 입차 시간이 출차 시간보다 앞선 시간이라면
      parkingFee = measureParkingFee(enterTime, exitTime);
    }
    else{
      throw new InvalidTimeException(carNum);
    }
    return checkUserMoneyEnough(carUser, parkingFee);
  }

  @Override
  //사용자가 충분한 돈을 가지고 있는지 검증
  public boolean checkUserMoneyEnough(User carUser, long parkingFee) {
    if(carUser.getAmount() >= parkingFee) { //자동차 소유주가 가지고 있는 돈이 주차요금보다 적다면
      return true;
    }
    throw new UserMoneyNotEnoughException(carUser, parkingFee);
  }

  //입출차 시간의 차이를 초 단위로 요금을 계산
  @Override
  public long measureParkingFee(LocalDateTime enterTime, LocalDateTime exitTime) {
    //머무른 날짜가 24시를 넘어간다면
    if(enterTime.getDayOfMonth() != exitTime.getDayOfMonth()){
      return measureParkingFee_over24(enterTime, exitTime);
    }
    //24시를 넘어가지 않는다면
    return measureParkingFee_under24(enterTime, exitTime);
  }

  //머무른 시간이 자정 24시를 넘어갔을 때 요금 구하기
  @Override
  public long measureParkingFee_over24(LocalDateTime enterTime, LocalDateTime exitTime) {

    //입차한 날 요금(첫 번째 날 요금)
    long enterDayFee = measureEnterDayParkingFee(enterTime);
    //출차할 날 요금(마지막 날 요금)
    long exitDayFee =  measureExitDayParkingFee(exitTime);

    //중간 날 요금(하루 최대 요금 * 머무른 날짜)
    int betweenParkDays =  exitTime.compareTo(enterTime) - 1; //머무른 날짜
    long betweenDayFee = ONE_DAY_MAX_PARKINGFEE * betweenParkDays;
    return enterDayFee + exitDayFee + betweenDayFee;
  }

  //머무른 시간이 자정 24시를 넘어가지 않았을 때 요금 구하기
  @Override
  public long measureParkingFee_under24(LocalDateTime enterTime, LocalDateTime exitTime) {
    return measureFeeBySeconds(Duration.between(enterTime, exitTime).getSeconds());
  }

  //입차한 시간부터 자정까지 요금 계산하는 메소드
  @Override
  public long measureEnterDayParkingFee(LocalDateTime enterTime) {
    //입차한 날 자정 시간
    LocalDateTime enterTime_midnight = LocalDateTime.of(enterTime.getYear(), enterTime.getMonth(), enterTime.getDayOfMonth() + 1, 0, 0,0);

    //입차한 날 머무른 초 시간
    Duration enterDayDuration = Duration.between(enterTime, enterTime_midnight);
    return measureFeeBySeconds(enterDayDuration.getSeconds());
  }

  //출차한날 자정부터 출차한 시간까지 요금 계산하는 메소드
  @Override
  public long measureExitDayParkingFee(LocalDateTime exitTime) {
    //출차한 날 자정 시간
    LocalDateTime exitTime_midnight = LocalDateTime.of(exitTime.getYear(), exitTime.getMonth(), exitTime.getDayOfMonth(), 0, 0,0);

    //출차한 날 머무른 초 시간
    Duration exitDayDuration = Duration.between(exitTime_midnight, exitTime);
    return measureFeeBySeconds(exitDayDuration.getSeconds());
  }

  @Override
  public long measureFeeBySeconds(long seconds) {
    if(under30minutes(seconds)){ //30분 이하라면 0원
      return 0;
    }
    if(under30minutesOver1hour(seconds)){ //30분 초과 1시간 이하라면 1000원
      return 1000;
    }
    return measureFee_over1hour(seconds);
  }

  //30분을 넘지않으면 TRUE
  @Override
  public boolean under30minutes(long seconds) {
    if(seconds <= 1800){
      return true;
    }
    return false;
  }

  //30분 이상 1시간 이하이면 TRUE
  public boolean under30minutesOver1hour(long seconds){
    if(1800 <= seconds && seconds <= 3600){
      return true;
    }
    return false;
  }

  public long measureFee_over1hour(long seconds) {
    long totalFee = 0;
    totalFee += FIRST_1HOUR_FEE;

    //10분당 추가 요금 500원 계산
    //(1시간을 제외한 시간) / 10분 * 10분당 추가 시간
    long reminder = (seconds - 3600) % 600; //10분 단위로 나누었을 때 나머지

    if(reminder > 0){ //10분 단위로 나누어 떨어지지 않는다면
      totalFee +=  ((seconds - 3600) / 600 + 1) * ADDFEE_PER_10MINUTE;
    }
    else{ //10분 단위로 나누어 떨어진다면
      totalFee +=  (seconds - 3600) / 600 * ADDFEE_PER_10MINUTE;
    }

    //하루 최대 요금을 넘어간다면 하루 최대 요금 지불
    if(totalFee > ONE_DAY_MAX_PARKINGFEE){
      return ONE_DAY_MAX_PARKINGFEE;
    }
    return totalFee;
  }
}
