package com.nhnacademy.waterworks.service;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

public interface FareRepository {

  public void loadAndSortByFare(String filePath)
      throws CsvValidationException, IOException;

  public List<List<String>> getCheapestFiveList();
  public void setFiveCheapestFareList(long waterUsage);

}
