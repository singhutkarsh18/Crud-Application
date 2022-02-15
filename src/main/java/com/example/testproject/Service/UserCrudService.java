package com.example.testproject.Service;

import com.example.testproject.Model.AppUser;

import java.util.List;

public interface UserCrudService {
    String createUser(AppUser appUser);
    List<AppUser> showAllUsers();
    AppUser showUser(Long id);
    String updateUser(AppUser appUser);
    String removeUser(Long id);
}
