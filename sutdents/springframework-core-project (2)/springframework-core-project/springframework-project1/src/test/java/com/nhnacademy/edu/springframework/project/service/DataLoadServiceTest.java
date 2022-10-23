package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

class DataLoadServiceTest {

  private static CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
  private static Scores mockScores = Mockito.mock(Scores.class);
  private static Students mockStudents = Mockito.mock(Students.class);

  @BeforeAll //제일 처음에 실행됨
  public static void init() throws NoSuchFieldException, IllegalAccessException {

    Field scores = csvDataLoadService.getClass().getDeclaredField("scores");
    Field students = csvDataLoadService.getClass().getDeclaredField("students");

    scores.setAccessible(true);
    students.setAccessible(true);

    scores.set(csvDataLoadService, mockScores);
    students.set(csvDataLoadService, mockStudents);
  }


  @Test
  void loadAndMerge() throws NoSuchFieldException, IllegalAccessException {
    //진짜 setScore이 되는지 1 A 30
    //순서 보장(load하고 merge하는 거)

    List<Score> scoreList = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      scoreList.add(new Score(i + 1, 15 * (i + 1)));
    }

    Mockito.when(mockScores.findAll()).thenReturn(scoreList);

    csvDataLoadService.loadAndMerge();

    InOrder inOrder = Mockito.inOrder(mockScores, mockStudents);

    inOrder.verify(mockScores, Mockito.times(1)).load();
    inOrder.verify(mockStudents, Mockito.times(1)).load();
    inOrder.verify(mockStudents, Mockito.times(1)).merge(Mockito.any());

  }
}