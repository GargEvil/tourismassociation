package com.tourism.tourismassociation.service;

import com.tourism.tourismassociation.DTO.UserDTO;
import com.tourism.tourismassociation.exceptions.UserServiceException;
import com.tourism.tourismassociation.helper.Utils;
import com.tourism.tourismassociation.model.User;
import com.tourism.tourismassociation.repository.UserRepository;
import com.tourism.tourismassociation.ui.response.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserRepository userRepository;

    @Autowired
    private Utils utils;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    public List<User> findAll(){ return userRepository.findAll(); }


    public User saveUser(User user){ return  userRepository.save(user); }


    @Override
    public UserDTO createUser(UserDTO user) {

        if(userRepository.findByEmail(user.getEmail()) != null)
            throw new UserServiceException(ErrorMessages.MAIL_RECORD_ALREADY_EXISTS.getErrorMessage());

        User userEntity = new User();

        BeanUtils.copyProperties(user,userEntity);

        String publicUserId = utils.generateUserId(15);
        userEntity.setUserId(publicUserId);

        userEntity.setPasswordHash(bCryptPasswordEncoder.encode(user.getPassword()));

        User storedUser = userRepository.save(userEntity);

        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(storedUser, returnValue);

        return returnValue;

    }


    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO getUser(String email){

        User userEntity = userRepository.findByEmail(email);

        if(userEntity == null)
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());


        UserDTO returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User userEntity = userRepository.findByEmail(email);

        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core
                .userdetails.User(userEntity.getEmail(),userEntity.getPasswordHash(), new ArrayList<>());
    }
}
