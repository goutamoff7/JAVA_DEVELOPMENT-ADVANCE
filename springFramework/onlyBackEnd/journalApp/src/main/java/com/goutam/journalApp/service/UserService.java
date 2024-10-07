package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createNewUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(List.of("USER"));
        return userRepository.save(user);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

//    public List<User> getAllUser() {
//        return userRepository.findAll();
//    }

    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public void deleteUser(String username) throws Exception {
        User user = getUserByUsername(username);
        userRepository.deleteById(user.getId());
        throw new Exception();
    }

    public User updateUser(String username,User updatedUser) throws Exception {
        User oldUser = getUserByUsername(username);
        if(oldUser!=null) {
            oldUser.
                    setPassword(updatedUser.getPassword() != null && !updatedUser.getPassword().equals("") ?
                            updatedUser.getPassword() : oldUser.getPassword());

            return createNewUser(oldUser);
        }
        else
            throw new Exception();
    }
}
