package com.skyline.service.impl;

import com.skyline.common.page.ResultInfo;
import com.skyline.common.utils.sequence.UUID32;
import com.skyline.entity.po.TUser;
import com.skyline.repository.UserRepository;
import com.skyline.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author skyline
 * @date 2017.11.18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<TUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public TUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public ResultInfo register(TUser user) {
        user.setUserId(UUID32.uuid());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));

        userRepository.save(user);
        return ResultInfo.success();
    }
}
