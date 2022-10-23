package com.nhnacademy.edu.springframework.project.repository;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ScoresTest {

  @Test
  void load() throws NoSuchFieldException, IllegalAccessException {
        /*
        CsvScores.getInstance().load();
        List<Score> score = CsvScores.getInstance().findAll();
        Assertions.assertThat(score).isNotEmpty();*/

    Scores scores = CsvScores.getInstance(); //CsvScores 클래스의 Instance를 생성
    scores.load(); //csv파일에서 객체 추가
    Field field = scores.getClass().getDeclaredField("scoreList"); //클래스에서 scoreList라는 이름을 가진 필드 반환
    field.setAccessible(true); //private 접근생성자 접근가능하게
    List<Score> scoreList = (List<Score>) field.get(scores); //scores 객체의 field 안에 저장된 값을 받아와서 list로 반환
    Assertions.assertThat(scoreList).isNotEmpty();
  }

  @Test
  void findAll() throws NoSuchFieldException, IllegalAccessException {
    //instance에 넣어서 두 개를 비교해서 똑같은지 확인한다.
       /*
        List<Score> scoreList = CsvScores.getInstance().findAll();
        Assertions.assertThat(scoreList).isNotEmpty(); */

    Scores scores = CsvScores.getInstance();
    Field field = scores.getClass().getDeclaredField("scoreList"); //scoreList라는 field를 꺼내옴
    field.setAccessible(true); //private을 접근하기 위한 용도

    List<Score> scoreList = new ArrayList<>();
    scoreList.add(new Score(1, 20));
    scoreList.add(new Score(2, 30));
    scoreList.add(new Score(3, 40));

    field.set(scores, scoreList); //score라는 객체의 클래스의 field에 scoreList를 강제주입

    Assertions.assertThat(scores.findAll()).isEqualTo(scoreList);
  }
}