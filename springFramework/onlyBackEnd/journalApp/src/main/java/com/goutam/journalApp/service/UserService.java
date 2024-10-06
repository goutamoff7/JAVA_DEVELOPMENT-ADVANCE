package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
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

    public void deleteUser(User user) throws Exception {
        try{
            userRepository.deleteById(user.getId());
        }
        catch(Exception e)
        {
            throw new Exception();
        }

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
