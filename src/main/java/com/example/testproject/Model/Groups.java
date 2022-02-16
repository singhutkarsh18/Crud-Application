package com.example.testproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor@NoArgsConstructor
@Entity
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private String groupName;


    @OneToMany(mappedBy = "groups")@JsonIgnore
    private Set<AppUser> appUsers=new HashSet<>();

    public void addUser(AppUser appUser) {
        appUsers.add(appUser);
    }
}
