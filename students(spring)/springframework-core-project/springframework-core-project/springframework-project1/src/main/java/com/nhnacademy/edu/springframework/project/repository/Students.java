package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.FileNotFoundException;
import java.util.Collection;

public interface Students {
    void load() throws FileNotFoundException;

    Collection<Student> findAll();

    void merge(Collection<Score> scores);
}
