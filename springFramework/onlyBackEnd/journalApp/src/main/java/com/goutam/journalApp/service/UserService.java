package com.goutam.journalApp.service;

import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void deleteUser(String username) throws Exception {
        User user = userRepository.findUserByUsername(username);
        if(user!=null)
            userRepository.deleteById(user.getId());
        else
            throw new Exception();
    }

    public User updateUser(String username,User updatedUser) throws Exception {
        User oldUser = getUserByUsername(username);
        if(oldUser!=null) {
            oldUser.
                    setPassword(updatedUser.getPassword() != null && !updatedUser.getPassword().equals("") ?
                            updatedUser.getPassword() : oldUser.getPassword());

            return userRepository.save(oldUser);
        }
        else
            throw new Exception();
    }
}
