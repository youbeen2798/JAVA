package com.nhnacademy.edu.springframework.project.repository;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvScores implements Scores {

    static CsvScores instance;

    private List<Score> scoreList  = new ArrayList<>();

    private CsvScores(){}

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        if(instance == null) {
            instance = new CsvScores();
        }
        return instance;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load(){

        File file = new File("C:/Users/yoube/Desktop/Java_backend/day9(Homework)/springframework-core-project (2)/springframework-core-project/springframework-project1/src/main/resources/data/score.csv");
//        File file = new File("src/main/resources/data/score.csv");
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                int studentNumber = Integer.parseInt(line.split(",")[0]);
                int studentScore = Integer.parseInt(line.split(",")[1]);
                scoreList.add(new Score(studentNumber, studentScore));
            }
        }
        catch(Exception e){
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
