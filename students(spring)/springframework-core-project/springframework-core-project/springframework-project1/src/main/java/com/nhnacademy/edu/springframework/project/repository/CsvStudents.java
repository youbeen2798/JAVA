package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvStudents implements Students {

    List<Student> studentList = new ArrayList<>();

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/


    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() throws FileNotFoundException {
    //    String filepath = "src/main/resources/data/score.csv";
        String filepath = "C:\\Users\\yoube\\Desktop\\Java_backend\\day9(Homework)\\springframework-core-project\\springframework-project1\\src\\main\\resources\\data\\student.csv";
    //    String filepath = "src/main/resources/data/student.csv";
        CSVReader csvReader = new CSVReader(new FileReader(filepath));
        try{
            String line[];
            while((line =  csvReader.readNext()) != null){
                int studentNumber = Integer.parseInt(line[0]);
                String studentScore = line[1];
        //        int studentNumber = Integer.parseInt(line[0].split(",")[0]); //1
        //        String studentScore = line[0].split(",")[1]; //A
                studentList.add(new Student(studentNumber, studentScore));
            }
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studentList;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {

        for(Score score : scores){
            int studentNumber = score.getStudentSeq();

            for(Student student : studentList){
                if(student.getSeq() == studentNumber){
                    student.setScore(score);
                    break;
                }
            }
        }
    }
}
