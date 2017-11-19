package com.skyline.service.impl;

import com.skyline.common.constants.message.MessageEnum;
import com.skyline.common.exception.SimpleServiceException;
import com.skyline.common.page.ResultInfo;
import com.skyline.entity.po.TUser;
import com.skyline.repository.UserRepository;
import com.skyline.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import javax.xml.transform.Source;

/**
 * @author skyline
 * @date 2017.11.19
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public ResultInfo login(String username, String password){
        TUser tUser = userRepository.findByUsername(username);
        //TODO 判断业务

        //spring security
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(upToken);
        }catch (AuthenticationException e){
            throw new SimpleServiceException(MessageEnum.LOGIN_FAIL.getCode(),e.getMessage());
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResultInfo.success();
    }
}
