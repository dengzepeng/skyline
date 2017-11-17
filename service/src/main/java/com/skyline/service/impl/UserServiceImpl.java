package com.skyline.service.impl;

import com.skyline.entity.po.TUser;
import com.skyline.repository.UserRepository;
import com.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<TUser> findAll() {
        return userRepository.findAll();
    }
}
