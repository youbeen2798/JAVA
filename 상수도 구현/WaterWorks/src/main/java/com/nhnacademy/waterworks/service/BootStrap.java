package com.nhnacademy.waterworks.service;

import com.opencsv.exceptions.CsvValidationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BootStrap {

  private static long waterUsage;

  public static void main(String[] args) throws CsvValidationException, IOException {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(WaterWorkConfig.class);

    FareRepository fareRepository =ac.getBean(FareRepository.class);
    ResultReport resultReport = ac.getBean(ResultReport.class);
    WaterSupplyChargeService waterSupplyChargeService = ac.getBean(WaterSupplyChargeService.class);

    //파일 로드하기
    File file = new File(".");
    String filePath = file.getAbsolutePath() + "\\WaterWorks\\src\\main\\resources\\data\\Tariff_20220331.csv";
    //파일 로드 후 요금으로 정렬하기
    fareRepository.loadAndSortByFare(filePath);

    Scanner scanner = new Scanner(System.in);
    System.out.print("당신의 물 사용량은 얼마입니까? ");
    waterUsage = scanner.nextLong();

    //가장 값싼 5개만 저장하기
    fareRepository.setFiveCheapestFareList(waterUsage);

    //가장 갑싼 5개의 리스트에 billTotal 더하기
    List<List<String>> listOrderedByBillTotal = waterSupplyChargeService.addBillTotal(waterUsage);

    //billTotal까지 더한 가장 갑싼 5개의 list로 WaterBill 객체들의 리스트 만들기
    resultReport.setFinalResultList(listOrderedByBillTotal);

    //가장 값싼 5개의 WaterBill 출력하기
    resultReport.printResultReport();



  }

}
