package com.nhnacademy.gw1.parking;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParkingSystemTest {

  private ParkingSystem parkingSystem;
  private DefaultPaySystem defaultPaySystem;
  private List<Entrance> entranceList;
  private List<Exit> exitList;
  private Car car;
  private User user;
  private Map<Long, LocalDateTime> enterMap;
  private Map<Long, LocalDateTime> exitMap;
  private Map<Long, User> carUserMap;

  @BeforeEach
  public void setUp() throws NoSuchFieldException, IllegalAccessException {

    this.entranceList = mock(ArrayList.class);
    for(int i = 0; i < 10; i++){
      entranceList.add(mock(Entrance.class));
    }

    this.exitList = mock(ArrayList.class);
    for(int i = 0; i < 10; i++){
      exitList.add(mock(Exit.class));
    }

    parkingSystem = new ParkingSystem(entranceList, exitList);

    Field entranceField = parkingSystem.getClass().getDeclaredField("entranceList");
    entranceField.setAccessible(true);
    entranceField.set(parkingSystem, entranceList);

    Field exitField = parkingSystem.getClass().getDeclaredField("exitList");
    exitField.setAccessible(true);
    exitField.set(parkingSystem, exitList);

    this.enterMap = mock(Map.class);
    Field enterMapField = parkingSystem.getClass().getDeclaredField("enterMap");
    enterMapField.setAccessible(true);
    enterMapField.set(parkingSystem, enterMap);

    this.exitMap = mock(Map.class);
    Field exitMapField = parkingSystem.getClass().getDeclaredField("exitMap");
    exitMapField.setAccessible(true);
    exitMapField.set(parkingSystem, exitMap);

    this.user = mock(User.class);
    this.car = mock(Car.class);
    when(this.car.getCarNum()).thenReturn(1L);
    when(this.car.getUser()).thenReturn(user);

    this.defaultPaySystem = mock(DefaultPaySystem.class);
    Field paySystemField = parkingSystem.getClass().getDeclaredField("paySystem");
    paySystemField.setAccessible(true);
    paySystemField.set(parkingSystem, defaultPaySystem);

    this.carUserMap = mock(Map.class);
    Field carUserMapField = parkingSystem.getClass().getDeclaredField("userMap");
    carUserMapField.setAccessible(true);
    carUserMapField.set(parkingSystem, carUserMap);
  }

  @DisplayName("주차장 시스템에서 입차 시간 담당하는 입구 테스트")
  @Test
  void parkingSystem_enter_then_enterance_enter_success(){
    //테스트는 입구 한개로만 테스트
    Entrance enterance = mock(Entrance.class);
    for(int i = 0; i<10; i++){
      when(entranceList.get(i)).thenReturn(enterance);
    }

    parkingSystem.enter(car);
    verify(enterance).enter(car);
  }

  @DisplayName("주차장 시스템에서 입차 시간을 잘 저장하는지 테스트")
  @Test
  void parkingSystem_enter_enterTime_success(){
    Entrance enterance = mock(Entrance.class);
    for(int i = 0; i<10; i++){
      when(entranceList.get(i)).thenReturn(enterance);
    }

    parkingSystem.enter(car);
    LocalDateTime enterTime = this.entranceList.get(0).enter(car);

    verify(enterMap).put(car.getCarNum(), enterTime);
  }

  @DisplayName("주차장 시스템에서 자동차 소유주를 잘 저장하는지 테스트")
  @Test
  void parkingSystem_enter_putUser_success(){
    Entrance enterance = mock(Entrance.class);
    for(int i = 0; i<10; i++){
      when(entranceList.get(i)).thenReturn(enterance);
    }

    parkingSystem.enter(car);
    verify(this.carUserMap).put(1L, user);
  }

  @DisplayName("주차장 시스템에서 출차 시간 담당하는 출구 테스트")
  @Test
  void parkingSystem_exit_then_exit_exit_success(){
    Exit exit = mock(Exit.class);
    for(int i = 0; i<10; i++){
      when(exitList.get(i)).thenReturn(exit);
    }

    parkingSystem.exit(car);
    verify(exit).exit(car);
  }

  @DisplayName("주차장 시스템에서 출차 시간을 잘 저장하는지 테스트")
  @Test
  void parkingSystem_enter_exitTime_success(){
    Exit exit = mock(Exit.class);
    for(int i = 0; i<10; i++){
      when(exitList.get(i)).thenReturn(exit);
    }
    parkingSystem.exit(car);

    LocalDateTime exitTime = this.exitList.get(0).exit(car);
    verify(exitMap).put(car.getCarNum(), exitTime);
  }

  @DisplayName("차의 고유 번호와 입출차 시간을 결제 시스템에 위임하는 테스트")
  @Test
  void parkingSystem_pay(){
    when(enterMap.get(car.getCarNum())).thenReturn(LocalDateTime.now());
    when(carUserMap.get(car.getCarNum())).thenReturn(user);
    when(exitMap.get(car.getCarNum())).thenReturn(LocalDateTime.now());

    parkingSystem.pay(car);
    verify(this.defaultPaySystem)
        .pay(car.getCarNum(), enterMap.get(car.getCarNum()),
            exitMap.get(car.getCarNum()), carUserMap.get(car.getCarNum()));
  }
}
