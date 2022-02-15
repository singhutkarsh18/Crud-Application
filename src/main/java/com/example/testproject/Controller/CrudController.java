package com.example.testproject.Controller;

import com.example.testproject.Model.AppUser;
import com.example.testproject.Service.Implementation.UserServiceImpl;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CrudController {
    private UserServiceImpl userService;
    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@RequestBody AppUser appUser)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(appUser));
        }
        catch (UnsupportedOperationException e)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getLocalizedMessage());
        }
    }
    @GetMapping("/get/allUsers")
    public ResponseEntity<?> getAllUsers()
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.showAllUsers());
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
    @GetMapping("/get/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.showUser(userId));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
    @PutMapping("/update/user")
    public ResponseEntity<?> updateUserDetails(@RequestBody AppUser appUser)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(appUser));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.removeUser(userId));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
}
