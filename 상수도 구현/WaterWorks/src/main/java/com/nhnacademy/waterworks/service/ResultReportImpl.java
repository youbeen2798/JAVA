package com.nhnacademy.waterworks.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ResultReportImpl implements ResultReport {

  List<WaterBill> finalResultList;

  //가장 값싼 5개의 rate를 담은 리스트를 waterBill 리스트에 담아주는 메소드
  public void setFinalResultList(List<List<String>> fiveCheapestFareList){
    this.finalResultList = new ArrayList<>();
    for(int i = 0; i < fiveCheapestFareList.size(); i++){
      List<String> tmpList = fiveCheapestFareList.get(i);
      WaterBill waterBill = new WaterBill(tmpList.get(1), tmpList.get(2), Integer.parseInt(tmpList.get(5)), Long.parseLong(tmpList.get(7)));
      finalResultList.add(waterBill);
    }
  }

  //billTota이 더해진 결과 리포트 출력하는 메소드
  @Override
  public void printResultReport() {
    for(WaterBill waterBill : finalResultList){
      System.out.println(waterBill);
    }

  }
}
