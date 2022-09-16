package com.alkemy.disney.disney.auth.service;


import com.alkemy.disney.disney.auth.dto.UserDTO;
import com.alkemy.disney.disney.auth.entity.UserEntity;
import com.alkemy.disney.disney.auth.repository.IUserRepository;
import com.alkemy.disney.disney.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IEmailService emailService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByUsername(username);
        if(userEntity==null)
                  throw new UsernameNotFoundException("username or password not found");
        return new User(userEntity.getUsername(),userEntity.getPassword(), Collections.emptyList());
    }

    public boolean save(UserDTO userDTO)
    {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserEntity userEntity=new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userEntity=userRepository.save(userEntity);
        if (userEntity!=null)
            emailService.sendWelcomeEmailTo(userEntity.getUsername());
        return userEntity!=null;

    }
}
