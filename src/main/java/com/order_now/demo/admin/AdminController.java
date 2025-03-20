package com.order_now.demo.admin;

import com.order_now.demo.user.User;
import com.order_now.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Test");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(this.userRepository.findAll());
    }
}
