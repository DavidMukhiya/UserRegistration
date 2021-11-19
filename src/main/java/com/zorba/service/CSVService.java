package com.zorba.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zorba.entity.Users;
import com.zorba.helper.CSVHelper;
import com.zorba.repository.UserRepository;


@Service
public class CSVService {
  @Autowired
  UserRepository userRepository;

  public void save(MultipartFile file) {
    try {
      List<Users> userLists = CSVHelper.csvToTutorials(file.getInputStream());
      userRepository.saveAll(userLists);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public List<Users> getAllTutorials() {
    return userRepository.findAll();
  }
}
