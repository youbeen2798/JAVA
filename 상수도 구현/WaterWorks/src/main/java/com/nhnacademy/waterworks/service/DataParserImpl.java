package com.nhnacademy.waterworks.service;

import static java.util.Arrays.asList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import org.springframework.stereotype.Component;

@Component
public class DataParserImpl implements DataParser{

  //요금을 중심으로 정렬하기
  PriorityQueue<List<String>> QueueorderedByFare = new PriorityQueue<>(new Comparator<List<String>>() {
    @Override
    public int compare(List<String> l1, List<String> l2) {
      int firstValue = Integer.parseInt(l1.get(6));
      int secondValue= Integer.parseInt(l2.get(6));
      if(firstValue == secondValue){
        return Integer.compare(secondValue, firstValue);
      }
      return Integer.compare(firstValue, secondValue);
    }
  });

  //우선순위 큐를 이용하여 요금으로 정렬하기
  public PriorityQueue<List<String>> csvDataParser(String filePath) throws IOException {

    File file = new File(filePath);
    BufferedReader br = Files.newBufferedReader(Paths.get(file.getPath()));

    //처음 글자 빼주기
    String line = "";
    line = br.readLine();
    while((line = br.readLine()) != null){
      String arr[] = line.split(",");
      List<String> tmpList = new ArrayList<>(asList(arr));
      QueueorderedByFare.add(tmpList);
    }
    return QueueorderedByFare;
  }



}
