package com.example.testproject.Service.Implementation;

import com.example.testproject.Model.AppUser;
import com.example.testproject.Model.Groups;
import com.example.testproject.Repository.GroupRepository;
import com.example.testproject.Repository.UserRepository;
import com.example.testproject.Service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;

@Service@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;
    private UserRepository userRepository;
    @Override
    public String createGroup(String groupName) {
        groupRepository.save(new Groups(null,groupName,null));
        return "Group Created";
    }



    @Override
    public String assignUsers(Long userId, Long groupId) {
        if(!userRepository.existsById(userId)||!groupRepository.existsById(groupId))
            throw new EntityNotFoundException("User/group not found");
        AppUser appUser = userRepository.findById(userId).get();
        Groups groups= groupRepository.findById(groupId).get();
        appUser.setGroups(groups);
        userRepository.save(appUser);
        return "User assigned to group";
    }

    @Override
    public List<Groups> showAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public String removeGroup(Long groupId) {
        if(!groupRepository.existsById(groupId))
            throw new EntityNotFoundException("Group not found");
        groupRepository.deleteById(groupId);
        return "Group deleted";
    }
}
