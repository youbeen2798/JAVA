package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.config.RepositoryConfig;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.FileNotFoundException;
import java.util.Collection;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) throws FileNotFoundException {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            RepositoryConfig.class);

        DataLoadService dataloadService = ctx.getBean(DataLoadService.class);

        dataloadService.loadAndMerge();

        StudentService studentService = ctx.getBean(StudentService.class);

        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> StduentsorderedByScore = studentService.getStudentsOrderByScore();
        System.out.println(StduentsorderedByScore);
    }
}
