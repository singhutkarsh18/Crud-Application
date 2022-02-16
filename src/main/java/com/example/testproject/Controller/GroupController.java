package com.example.testproject.Controller;

import com.example.testproject.Service.Implementation.GroupServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
@AllArgsConstructor
@RestController@RequestMapping("/api")
public class GroupController {
    private GroupServiceImpl groupService;

    @PostMapping("/add/group/{groupName}")
    public ResponseEntity<?> addGroup(@PathVariable("groupName") String groupName)
    {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(groupService.createGroup(groupName));
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }
    @PostMapping("/assignUser/{userId}/{groupId}")
    public ResponseEntity<?> addUserToGroup(@PathVariable("userId") Long userId,@PathVariable("groupId") Long groupId)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(groupService.assignUsers(userId,groupId));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
    @GetMapping("/get/groups")
    public ResponseEntity<?> getAllGroups()
    {
            return ResponseEntity.status(HttpStatus.OK).body(groupService.showAllGroups());
    }
    @DeleteMapping("/delete/group/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable("groupId") Long groupId)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(groupService.removeGroup(groupId));
        }
        catch (EntityNotFoundException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getLocalizedMessage());
        }
    }
}
