package com.splitwise.demo.services;

import com.splitwise.demo.exceptions.UserException;
import com.splitwise.demo.models.User;
import com.splitwise.demo.repositories.UserRepository;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public User registerUser(String username, String password, String phoneNo){
        User user = new User();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(password);
        user.setPassword(encode);
        user.setUsername(username);
        user.setPhoneNo(phoneNo);
        user = userRepository.save(user);
        return user;
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void login(String username,String password) throws UserException, InterruptedException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UserException("User does not exist.");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(password,user.get().getPassword())){
            throw new UserException("User does not exist.");
        }
    }

    public User getUserById(int id) throws UserException {
        Optional<User> user = this.userRepository.findById(id);
        if(user.isEmpty()){
            throw new UserException("User not found");
        }

        return user.get();
    }
}
