package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    void load() throws NoSuchFieldException, IllegalAccessException {
        /*
        Students instance = CsvStudents.getInstance();
        instance.load();
        List<Student> studentList = (List<Student>) instance.findAll();
        Assertions.assertThat(studentList).isNotEmpty(); */

        Students students = CsvStudents.getInstance();
        students.load();
        Field field = students.getClass().getDeclaredField("studentList");
        List<Student> studentList = (List<Student>)field.get(students);
        Assertions.assertThat(studentList).isNotEmpty();
    }

    @Test
    void findAll() throws NoSuchFieldException, IllegalAccessException {
        /*
        List<Student> studentList = (List<Student>) CsvStudents.getInstance().findAll();
        Assertions.assertThat(studentList).isNotEmpty(); */
        Students students = CsvStudents.getInstance();
        Field field = students.getClass().getDeclaredField("studentList"); //studentList라는 필드 가져오기
        field.setAccessible(true);

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1, "A"));
        studentList.add(new Student(2, "B"));
        studentList.add(new Student(3, "C"));

        field.set(students, studentList);

        Assertions.assertThat(students.findAll()).isEqualTo(studentList);
    }

    @Test
    void merge() throws NoSuchFieldException, IllegalAccessException {
       /*
        Collection<Score> score = null;
        score.add(new Score(1, 85));
        score.add(new Score(2, 90));
        Students instance = CsvStudents.getInstance();
        instance.merge(score);
        */

        Collection<Score> scores = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();

        for(int i = 1; i<= 10; i++){
            scores.add(new Score(i,80 + i));
        }

        int grade = 65;
        for(int i = 1; i<= 10; i++){
            studentList.add(new Student(i, String.valueOf((char)grade++)));
        }

        Students students = CsvStudents.getInstance();
        Field field = students.getClass().getDeclaredField("studentList"); //studentList라는 필드 가져오기
        field.setAccessible(true);

        field.set(students, studentList);

        students.merge(scores);
        List<Student> resultStudent = (List<Student>)field.get(students);

        for(Student student : resultStudent){
            Assertions.assertThat(student.getSeq()).isEqualTo(student.getScore().getStudentSeq());
        }

    }
}