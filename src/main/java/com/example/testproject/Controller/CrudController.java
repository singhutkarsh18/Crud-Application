package com.example.testproject.Controller;

import com.example.testproject.Model.AppUser;
import com.example.testproject.Service.Implementation.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CrudController {
    private UserServiceImpl userService;

    @Operation(summary = "Use this to add a user")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "201", description = "Successfully added user to db",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "417", description = "User already present",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "406", description = "Invalid email/mobile",content = {@Content(mediaType = "application/json")})
        }
    )
    @PostMapping("/add/user")
    public ResponseEntity<?> addUser(@RequestBody AppUser appUser)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(appUser));
        }
        catch (UnsupportedOperationException e1)
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e1.getLocalizedMessage());
        }
        catch (IllegalStateException e2)
        {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e2.getLocalizedMessage());
        }
    }
    @Operation(summary = "Use this to get all users")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "All users are shown",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "No user found in db",content = {@Content(mediaType = "application/json")})
    }
    )
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
    @Operation(summary = "Use this to get user by id")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "User is shown",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found",content = {@Content(mediaType = "application/json")})
    }
    )
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
    @Operation(summary = "Use this to update user details")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "User details are updated",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "User not found in db",content = {@Content(mediaType = "application/json")})
    }
    )
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
    @Operation(summary = "Use this to delete a user")
    @ApiResponses(value= {
            @ApiResponse(responseCode = "200", description = "User deleted",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Requested user not found in db",content = {@Content(mediaType = "application/json")})
    }
    )
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
