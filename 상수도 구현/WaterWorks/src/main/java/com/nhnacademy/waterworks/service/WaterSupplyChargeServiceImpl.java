package com.nhnacademy.waterworks.service;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class WaterSupplyChargeServiceImpl implements WaterSupplyChargeService{

  private static FareRepository fareRepository;

  //생성자 주입
  public WaterSupplyChargeServiceImpl(FareRepository fareRepository){
    this.fareRepository = fareRepository;
  }

  //가장 값싼 5개를 담고 있는 리스트에 billTotal을 더해주는 메소드
  public List<List<String>> addBillTotal(long waterUsage){

    List<List<String>> fiveCheapestFareList = fareRepository.getCheapestFiveList();

    for(int i = 0; i< fiveCheapestFareList.size(); i++){
      List<String> tmpList = fiveCheapestFareList.get(i);
      long billTotal = Integer.parseInt(tmpList.get(6)) * waterUsage;
      fiveCheapestFareList.get(i).add(String.valueOf(billTotal));
    }

    return fiveCheapestFareList;
  }

}
