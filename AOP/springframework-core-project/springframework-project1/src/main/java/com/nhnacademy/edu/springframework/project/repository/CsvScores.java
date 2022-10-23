package com.nhnacademy.edu.springframework.project.repository;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvScores implements Scores {

    private List<Score> scoreList  = new ArrayList<>();

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/


    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load(){
       // String filepath = "src/main/resources/data/score.csv";
        String filepath = "C:\\Users\\yoube\\Desktop\\Java_backend\\day9(Homework)\\springframework-core-project\\springframework-project1\\src\\main\\resources\\data\\score.csv";
        try{
            CSVReader csvReader = new CSVReader(new FileReader(filepath));
            String line[];
            while((line = csvReader.readNext()) != null){
                int studentNumber = Integer.parseInt(line[0]);
                int studentScore = Integer.parseInt(line[1]);
        //        int studentNumber = Integer.parseInt(line[0].split(",")[0]);
        //        int studentScore = Integer.parseInt(line[0].split(",")[1]);
                scoreList.add(new Score(studentNumber, studentScore));
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreList;
    }
}
