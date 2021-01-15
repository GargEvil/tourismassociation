package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> findAll(){ return userRepository.findAll(); }

    public User save(User user){ return  userRepository.save(user); }

}
