package com.blogPostApp.blogserver.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.blogPostApp.blogserver.entities.User;
import com.blogPostApp.blogserver.services.UserService;

@RestController
@CrossOrigin(origins ="http://localhost:5173" )
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    
    // User registration
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userService.registerUser(user);
        
        
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    
    @PostMapping("/registerUserId")
    public  ResponseEntity<Integer> registerUserID(@RequestBody User user) {
        // Save the user to the database (you may want to hash the password)
    	User newUser = userService.registerUser(user);

        // The user ID is automatically generated and can be accessed from the saved user entity
        int generatedUserId = user.getId();

        return ResponseEntity.status(HttpStatus.CREATED).body(generatedUserId);
    }

    // User login
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User user) {
        User loggedInUser = userService.loginUser(user.getUsername(), user.getPassword());
        if (loggedInUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }

    // Edit user profile
    @PutMapping("/{userId}")
    public ResponseEntity<User> editUserProfile(@PathVariable int userId, @RequestBody User updatedUser) {
        User editedUser = userService.editUserProfile(userId, updatedUser);
        if (editedUser == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(editedUser, HttpStatus.OK);
    }
}
