package com.nhnacademy.edu.springframework.project.service;

import java.io.FileNotFoundException;

public interface DataLoadService {
    void loadAndMerge() throws FileNotFoundException;
}
