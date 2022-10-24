package com.nhnacademy.waterworks.service;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FareRepositoryImpl implements FareRepository{
  private static DataParser dataParser;
  PriorityQueue<List<String>> QueueOrderedByFare = new PriorityQueue<>();
  List<List<String>> fiveCheapestFareList = new ArrayList<>();

  //생성자 주입
  @Autowired
  public FareRepositoryImpl(DataParser dataParser){
    this.dataParser = dataParser;
  }

  //filePath에 있는 파일의 정보들을 요금을 중심으로 정렬하기
  @Override
  public void loadAndSortByFare(String filePath) throws CsvValidationException, IOException {
    this.QueueOrderedByFare = dataParser.csvDataParser(filePath);
  }

  //가장 요금이 값싼 5개의 리스트만 가져오기
  public List<List<String>> getCheapestFiveList(){
    return this.fiveCheapestFareList;
  }

  //가장 요금이 값싼 5개의 리스트 만들기
  @Override
  public void setFiveCheapestFareList(long waterUsage) {

    for(int i = 0; i< 5; i++){
      fiveCheapestFareList.add(QueueOrderedByFare.poll());
    }
  }
}
