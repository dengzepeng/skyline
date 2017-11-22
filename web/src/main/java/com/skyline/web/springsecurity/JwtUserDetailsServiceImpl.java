package com.skyline.web.springsecurity;

import com.skyline.entity.po.TUser;
import com.skyline.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * spring security UserDeatils接口实现
 * @author skyline
 * @date 2017.11.19
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final TUser user = userRepository.findByUsername(username);
        if (user == null) {
            //必须抛出UsernameNotFoundException异常
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    //TODO:权限列表
                    return null;
                }

                @Override
                public String getPassword() {
                    return user.getPassword();
                }

                @Override
                public String getUsername() {
                    return username;
                }

                /**
                 * 用户帐号已过期
                 * @return
                 */
                @Override
                public boolean isAccountNonExpired() {
                    //TODO 是否过期 true 否
                    return true;
                }

                /**
                 * 是否锁定 true未锁定
                 * @return
                 */
                @Override
                public boolean isAccountNonLocked() {
                    //TODO 是否锁定
                    return true;
                }

                /**
                 * 坏的凭证 true好的凭证
                 * @return
                 */
                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                /**
                 * 是否失效 true未失效
                 * @return
                 */
                @Override
                public boolean isEnabled() {
                    //TODO 是否失效
                    return true;
                }
            };
        }
    }
}
