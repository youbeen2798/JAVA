package com.nhnacademy.gw1.parking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExitTest {

  private Exit exit;
  private Car car;

  @BeforeEach
  void setUp(){
    this.exit = new Exit();
    this.car = mock(Car.class);
  }

  @Test
  @DisplayName("차의 출차 시간이 잘 리턴되는지 성공 테스트")
  void exit_then_returnLocalDateTime_success(){
    assertThat(this.exit.exit(car)).isInstanceOf(LocalDateTime.class);
  }
}
