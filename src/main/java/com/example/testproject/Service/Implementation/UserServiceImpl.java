package com.example.testproject.Service.Implementation;

import com.example.testproject.Model.AppUser;
import com.example.testproject.Repository.UserRepository;
import com.example.testproject.Service.UserCrudService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
@Service@AllArgsConstructor
public class UserServiceImpl implements UserCrudService {
    private UserRepository userRepository;
    @Override
    public String createUser(AppUser appUser) {
        if(userRepository.existsByEmailId(appUser.getEmailId()))
            throw new UnsupportedOperationException("User already present with email "+appUser.getEmailId());
        userRepository.save(appUser);
        return "User added successfully";
    }

    @Override
    public List<AppUser> showAllUsers() {
        if(userRepository.findAll().isEmpty())
            throw new EntityNotFoundException("No user found");
        return userRepository.findAll();
    }

    @Override
    public AppUser showUser(Long id) {
        if(userRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("User not found");
        return userRepository.findById(id).get();
    }

    @Override
    public String updateUser(AppUser appUser) {
        if(userRepository.findById(appUser.getId()).isEmpty())
            throw new EntityNotFoundException("User not found");
        userRepository.save(appUser);
        return "User updated";
    }

    @Override
    public String removeUser(Long id) {
        if(userRepository.findById(id).isEmpty())
            throw new EntityNotFoundException("User not found with id "+id);
        userRepository.deleteById(id);
        return "User deleted";
    }
}
