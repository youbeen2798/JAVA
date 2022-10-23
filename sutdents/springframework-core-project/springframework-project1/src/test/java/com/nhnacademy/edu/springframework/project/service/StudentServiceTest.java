package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StudentServiceTest {

    private static DefaultStudentService defaultStudentService = new DefaultStudentService();
    private static Students mockStudents = Mockito.mock(Students.class);
    private static Scores mockScores = Mockito.mock(Scores.class);

    @BeforeAll
    public static void init() throws NoSuchFieldException, IllegalAccessException {
        Field studentfield = defaultStudentService.getClass().getDeclaredField("studentRepository");
        studentfield.setAccessible(true);
        studentfield.set(defaultStudentService, mockStudents);

    }

    @Test
    void getPassedStudents() {

        List<Student> studentList = new ArrayList<>();

        int passedStudentNum = 0;
        for(int i = 0; i < 15; i++){
            Student student = new Student(i, "a" + i);
            Score score = new Score(i, 40 + i * 4);
            student.setScore(score);
            studentList.add(student);

            if(score.getScore() >= 60){
                passedStudentNum++;
            }
        }

        Mockito.when(mockStudents.findAll()).thenReturn(studentList);
        Collection<Student> passedstudents = defaultStudentService.getPassedStudents();
        Assertions.assertThat(passedstudents).hasSize(passedStudentNum);

    }

    @Test
    void getStudentsOrderByScore() {

        List<Student> studentList = new ArrayList<>();
        List<Score> scoreList = new ArrayList<>();

        for(int i = 0; i < 15; i++){
            Student student = new Student(i, "a" + i);
            Score score = new Score(i, 40 + i * 4);
            student.setScore(score);
            studentList.add(student);
            scoreList.add(score);
        }

        Mockito.when(mockStudents.findAll()).thenReturn(studentList);
        Collection<Student> studentsOrderedByScore =  defaultStudentService.getStudentsOrderByScore();

        List<Score> orderedScoreList = studentsOrderedByScore.stream().map(v -> v.getScore()).collect(
            Collectors.toList());

        Collections.reverse(scoreList);

        Assertions.assertThat(orderedScoreList).isEqualTo(scoreList);
    }
}