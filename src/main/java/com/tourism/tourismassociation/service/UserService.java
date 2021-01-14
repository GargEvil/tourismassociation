package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> findAll(){ return new ArrayList<>(); }

    public User save(User user){ return  new User(); }

}
