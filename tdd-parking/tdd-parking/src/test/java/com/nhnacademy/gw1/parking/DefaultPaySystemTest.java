package com.nhnacademy.gw1.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nhnacademy.gw1.parking.exception.UserMoneyNotEnoughException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DefaultPaySystemTest {

  private DefaultPaySystem defaultPaySystem;
  private User user;
  private static final long ONE_DAY_MAX_PARKINGFEE = 10000; //일일 주차 최대 요금

  @BeforeEach
  void setUp(){
    defaultPaySystem = new DefaultPaySystem();
    user = mock(User.class);
  }

  @Test
  @DisplayName("차 소유주가 가진 돈이 요금보다 많을 때 성공 테스트")
  void checkUserMoneyEnough_success() {
    when(user.getAmount()).thenReturn(10000L);

    long parkingFee = 5000L;
    assertThat(defaultPaySystem.checkUserMoneyEnough(user, parkingFee)).isTrue();
  }

  @Test
  @DisplayName("차 소유주가 가진 돈이 요금보다 많을 때 실패 테스트")
  void checkUserMoneyEnough_fail() {
    when(user.getAmount()).thenReturn(1000L);

    long parkingFee = 5000L;
    assertThatThrownBy(() -> defaultPaySystem.checkUserMoneyEnough(user, parkingFee))
        .isInstanceOf(UserMoneyNotEnoughException.class)
            .hasMessageContainingAll("money is Not Enough");
  }

  @Test
  @DisplayName("차가 하루 1시간 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_oneHour() {
    //최초 30분 1000원 + 30분(1500원 <= 10분마다 500원)
    //총 2500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,3,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(2500L),
        () ->     assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(2500L));
  }

  @Test
  @DisplayName("차가 하루 2시간 59분 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_twoHour_59minutes() {
    //최초 30분 1000원 + 2시간 29분(149분 : 7500원 <= 10분마다 500원)
    //총 8500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,4,59);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(8500L),
        () ->     assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(8500L));
  }

  @Test
  @DisplayName("차가 하루 2시간 59분 59초 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_twoHour_59minutes_59seconds() {
    //최초 30분 1000원 + 2시간 29분 59초 (149분 59초 : 7500원 <= 10분마다 500원)
    //총 8500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,4,59);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(8500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(8500L));
  }

  @Test
  @DisplayName("차가 하루 3시간 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_threeHour() {
    //최초 30분 1000원 + 2시간 30분(150분 : 7500원 <= 10분마다 500원)
    //총 8500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,5,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(8500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(8500L));
  }

  @Test
  @DisplayName("차가 하루 3시간 1분 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_threeHour_oneMinute() {
    //최초 30분 1000원 + 2시간 31분(151분 : 8000원 <= 10분마다 500원)
    //총 9000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,5,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(9000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(9000L));
  }

  @Test
  @DisplayName("차가 하루 3시간 1초 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_threeHour_oneSecond() {
    //최초 30분 1000원 + 2시간 30분 1초(150분 1초: 8000원 <= 10분마다 500원)
    //총 9000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,5,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(9000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(9000L));
  }


  @Test
  @DisplayName("차가 하루 59분 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_59minutes() {
    //최초 30분 1000원 + 29분(1500원 <= 10분마다 500원)
    //총 2500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,3,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(2500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(2500L));
  }

  @Test
  @DisplayName("차가 하루 59분 59초주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_59minutes_59seconds() {
    //최초 30분 1000원 + 29분 59초(1500원 <= 10분마다 500원)
    //총 2500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,3,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(2500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(2500L));
  }

  @Test
  @DisplayName("차가 하루 1시간 1초 주차했을 때 요금 테스트")
  void measureParkingFee_under24_check_oneHour_oneSeond() {
    //최초 30분 1000원 + 30분 1초(2000원 <= 10분마다 500원)
    //총 3000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,3,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(3000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_under24(enterTime, exitTime)).isEqualTo(3000L));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때 가정 후 리턴 타입 테스트")
  void measureParkingFee_over24_check_returnType() {
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,2,2);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,2,2);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureEnterDayParkingFee(enterTime)).isInstanceOf(Long.class),
        () -> assertThat(defaultPaySystem.measureExitDayParkingFee(exitTime)).isInstanceOf(Long.class));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때 요금 테스트")
  void measureParkingFee_over24_feeTest() {
    //첫날 : 1시간, 마지막 날 1시간 ==> 2500 + 2500 = 5000
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,0);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,1,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(5000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(5000L));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때, 입차한 날 1시간 1분 주차 경우 요금 테스트")
  void measureParkingFee_over24_feeTest_enterDay_over_oneMinute() {
    //첫날 : 1시간 1분, 마지막 날 1시간 ==> 3000 + 2500 = 5500
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,22,59);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,1,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(5500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(5500L));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때, 입차한 날 1시간 1초 주차 경우 요금 테스트")
  void measureParkingFee_over24_feeTest_enterDay_over_oneSecond() {
    //첫날 : 1시간 1초, 마지막 날 1시간 ==> 3000 + 2500 = 5500
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,22,59, 59);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,1,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(5500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(5500L));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때, 입차한 날 1시간 1분 주차 경우 요금 테스트")
  void measureParkingFee_over24_feeTest_exitDay_over_oneMinute() {
    //첫날 : 1시간, 마지막 날 1시간 1분 ==> 2500 + 3000 = 5500
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,1,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(5500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(5500L));
  }

  @Test
  @DisplayName("차 입출차 날짜가 자정 24시를 넘었을 때, 출차한 날 1시간 1초 주차 경우 요금 테스트")
  void measureParkingFee_over24_feeTest_exitDay_over_oneSecond() {
    //첫날 : 1시간 = 2500원, 마지막 날 1시간 1초 = 3000원
    //총 2500 + 3000 = 5500
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,4,1,0,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(5500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(5500L));
  }

  @Test
  @DisplayName("차가 3일 연속 주차했을 때(첫째날 1시간, 둘째날 하루종일, 셋째날 1시간)")
  void measureParkingFee_over24_feeTest_parked_3days() {
    //첫째날 1시간 = 2500원, 둘째날 하루종일 = 10000원, 셋째날 1시간 = 2500원
    //총 2500 + 10000 + 2500 = 15000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,5,1,0,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(15000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(15000L));
  }

  @Test
  @DisplayName("차가 3일 연속 주차했을 때(첫째날 1시간, 둘째날 하루종일, 셋째날 1시간 1초)")
  void measureParkingFee_over24_feeTest_parked_3days_extiDay_overOneSeconds() {
    //첫째날 1시간 = 2500원, 둘째날 하루종일 = 10000원, 셋째날 1시간 1초= 3000원
    //총 15500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,5,1,0,1);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(15500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(15500L));
  }

  @Test
  @DisplayName("차가 3일 연속 주차했을 때(첫째날 1시간, 둘째날 하루종일, 셋째날 10시간)")
  void measureParkingFee_over24_feeTest_parked_3days_over_ONE_DAY_MAX_PARKINGFEE_twice() {
    //첫째날 1시간 = 2500원, 둘째날 하루종일 = 10000원, 셋째날 10시간= 10000원(최대요금 넘음)
    //총 22500원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,23,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,5,10,0,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(22500L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(22500L));
  }

  @Test
  @DisplayName("차 주차 요금이 하루 최대 요금을 넘었을 때, 차 소유주가 10000원 이상을 가지고 있을 때 성공 테스트")
  void measureParkingFee_over_ONE_DAY_MAX_PARKINGFEE_carUser_haveMoney_then_success() {
    //요금이 하루 최대요금을 넘어 10000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,1,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,15,0);

    when(user.getAmount()).thenReturn(10000L);

    assertThat(defaultPaySystem.pay(1L, enterTime, exitTime, user)).isTrue();
  }

  @Test
  @DisplayName("차 주차 요금이 하루 최대 요금을 넘었을 때, 차 소유주가 10000원이 없을 때 에러 테스트")
  void measureParkingFee_over_ONE_DAY_MAX_PARKINGFEE_carUser_NothaveMoney_then_Exception() {
    //요금이 하루 최대요금을 넘어 10000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,3,1,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,15,0);

    when(user.getAmount()).thenReturn(9000L);
    assertThatThrownBy(() -> defaultPaySystem.pay(1L, enterTime, exitTime, user))
        .isInstanceOf(UserMoneyNotEnoughException.class)
            .hasMessageContainingAll("money is Not Enough");
  }

  @Test
  @DisplayName("차 주차 요금이 2일 연속 하루 최대 요금을 넘었을 때 요금 테스트")
  void measureParkingFee_over_ONE_DAY_MAX_PARKINGFEE_2days() {
    //이틀 연속 하루 최대요금 = 2 * 10000 = 20000
    LocalDateTime enterTime = LocalDateTime.of(2022,11,1,1,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,2,15,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(20000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(20000L));
  }

  @Test
  @DisplayName("차 주차 요금이 3일 연속 하루 최대 요금을 넘었을 때 요금 테스트")
  void measureParkingFee_over_ONE_DAY_MAX_PARKINGFEE_3days() {
    //삼일 연속 하루 최대요금 = 3 * 10000 = 30000
    LocalDateTime enterTime = LocalDateTime.of(2022,11,1,1,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,15,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(30000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(30000L));
  }

  @Test
  @DisplayName("차가 자정에 들어와서 이틀 뒤 자정에 나갔을 때 요금 테스트")
  void measureParkingFee_enter_24clock_exit_24clock() {
    //이틀 연속 하루 최대 요금 = 10000원 * 2 = 20000원
    LocalDateTime enterTime = LocalDateTime.of(2022,11,1,0,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,3,0,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(20000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(20000L));
  }

  @Test
  @DisplayName("차 주차 요금이 10일 연속 하루 최대 요금을 넘었을 때 요금테스트")
  void measureParkingFee_over_ONE_DAY_MAX_PARKINGFEE_10days() {
    //10일 연속 하루 최대요금 = 10 * 10000 = 100000
    LocalDateTime enterTime = LocalDateTime.of(2022,11,1,1,00);
    LocalDateTime exitTime = LocalDateTime.of(2022,11,10,15,0);

    assertAll("test",
        () -> assertThat(defaultPaySystem.measureParkingFee(enterTime,exitTime)).isEqualTo(100000L),
        () -> assertThat(defaultPaySystem.measureParkingFee_over24(enterTime, exitTime)).isEqualTo(100000L));
  }

}
