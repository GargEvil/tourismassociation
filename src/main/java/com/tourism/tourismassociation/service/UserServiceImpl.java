package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserRepository userRepository;


    public List<User> findAll(){ return userRepository.findAll(); }


    public User save(User user){ return  userRepository.save(user); }

    @Override
    public UserDTO createUser(UserDTO user) {
        User userEntity = new User();

        BeanUtils.copyProperties(user,userEntity);

        userEntity.setUserId("42523");
        userEntity.setPasswordHash("nafanfak");
        User storedUser = userRepository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;

    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }



}
