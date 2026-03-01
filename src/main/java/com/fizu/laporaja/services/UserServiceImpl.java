package com.fizu.laporaja.services;

import com.fizu.laporaja.model.entity.User;
import com.fizu.laporaja.model.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByNis(username);
        if(optionalUser.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return optionalUser.get();
    }


}
