package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class GradeQueryServiceTest {
    private static DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();
    private static Scores mockScores = Mockito.mock(Scores.class);
    private static Students mockStudents = Mockito.mock(Students.class);

    @BeforeAll
    public static void init() throws NoSuchFieldException, IllegalAccessException {
        //Score과 CsvStudents와
        Field scores = defaultGradeQueryService.getClass().getDeclaredField("scores");
        Field students = defaultGradeQueryService.getClass().getDeclaredField("students");

        scores.setAccessible(true);
        students.setAccessible(true);

        scores.set(defaultGradeQueryService, mockScores);
        students.set(defaultGradeQueryService, mockStudents);
    }

    @Test
    void getScoreByStudentName() {
        List<Student> studentList = new ArrayList<Student>();

        List<String> studentNames = Arrays.asList("apple", "banana", "cat", "dog", "egg");

        for(int i = 0; i<5; i++){
            Student student = new Student(i, studentNames.get(i));
            student.setScore(new Score(i,80 + i*2));
            studentList.add(student);
        }

        Mockito.when(mockStudents.findAll()).thenReturn(studentList);
        List<Score> expectedScores = defaultGradeQueryService.getScoreByStudentName(studentList.get(0).getName());

        List<Score> assertScores = new ArrayList<Score>();

        for(int i = 0; i<5; i++){
            if(studentList.get(i).getName() == studentNames.get(0)){
                assertScores.add(studentList.get(i).getScore());
            }
        }

        Assertions.assertThat(expectedScores).isEqualTo(assertScores);

    }

    @Test
    void getScoreByStudentSeq() {

        List<Score> scoreList = new ArrayList<Score>();

        for(int i = 0; i< 10; i++){
            scoreList.add(new Score(i, 80*2 + i));
        }

        Mockito.when(mockScores.findAll()).thenReturn(scoreList);
        Score score = defaultGradeQueryService.getScoreByStudentSeq(scoreList.get(0).getStudentSeq());
        Assertions.assertThat(score).isEqualTo(scoreList.get(0));

    }
}