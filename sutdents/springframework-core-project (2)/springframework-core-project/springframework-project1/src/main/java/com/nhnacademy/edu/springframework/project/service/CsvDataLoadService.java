package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Component;

@Component
public class CsvDataLoadService implements DataLoadService {

    Scores scores;
    Students students;
    public CsvDataLoadService(){
        this.scores = CsvScores.getInstance();
        this.students = CsvStudents.getInstance();
    }

    @Override
    public void loadAndMerge() {
        scores.load();
        //1 30

        students.load();
        //1 A
        students.merge(scores.findAll());
    }
}
