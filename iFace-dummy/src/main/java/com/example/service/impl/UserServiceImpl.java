package com.example.service.impl;

import com.example.model.User;
import com.example.response.UserResponse;
import com.example.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Arka", "Das", "ADMIN"));
        users.add(new User(2, "Amey", "Utpat", "USER"));
        users.add(new User(3, "Sisir", "Sarango", "USER"));
    }

    @Override
    public ResponseEntity generateReport(User user) {
        UserResponse userResponse = new UserResponse();
        if(user.getId()==null){
            userResponse.setMessage("Id is null");
            userResponse.setStatus(HttpStatus.BAD_REQUEST);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userResponse);
        }

        for(User listUser: users){
            if(user.getId()==listUser.getId() &&
                    user.getFirstName().equalsIgnoreCase(listUser.getFirstName()) &&
                    user.getLastName().equalsIgnoreCase(listUser.getLastName()) &&
                    user.getRole().equalsIgnoreCase(listUser.getRole())){

                userResponse.setMessage("Operation successful");
                userResponse.setStatus(HttpStatus.OK);
                return ResponseEntity.status(HttpStatus.OK).body(userResponse);
            }
        }
        userResponse.setMessage("User not found");
        userResponse.setStatus(HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponse);
    }
}
