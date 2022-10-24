package com.nhnacademy.waterworks.service;

import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;

public interface DataParser {

  public PriorityQueue<List<String>> csvDataParser(String filePath) throws CsvValidationException, IOException;

}
