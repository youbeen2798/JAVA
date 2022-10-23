package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import java.io.FileNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CsvDataLoadService implements DataLoadService {

  Scores scores;
  Students students;

  public CsvDataLoadService(Scores scores, Students students) {
    this.scores = scores;
    this.students = students;
  }

  @Override
  public void loadAndMerge() throws FileNotFoundException {
    scores.load();
    //1 30

    students.load();
    //1 A
    students.merge(scores.findAll());
  }
}
