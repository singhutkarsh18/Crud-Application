package com.example.testproject.Service;


import com.example.testproject.Model.Groups;

import java.util.List;

public interface GroupService {
    String createGroup(String groupName);
    String assignUsers(Long userId, Long groupId);
    List<Groups> showAllGroups();
    String removeGroup(Long groupId);
}
