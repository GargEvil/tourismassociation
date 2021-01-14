package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){ return userRepository.findAll(); }

    public User save(User user){ return  new User(); }

}
