package com.example.service;

import com.example.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity generateReport(User user);

}
