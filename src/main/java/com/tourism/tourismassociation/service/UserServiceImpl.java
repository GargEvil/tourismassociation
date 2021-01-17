package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public List<User> findAll(){ return userRepository.findAll(); }


    public User save(User user){ return  userRepository.save(user); }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
