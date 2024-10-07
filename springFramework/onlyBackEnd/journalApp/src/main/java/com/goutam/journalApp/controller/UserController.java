package com.goutam.journalApp.controller;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.service.JournalEntryService;
import com.goutam.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    //localhost:8080/user


    //localhost:8080/user
//    @GetMapping
//    public ResponseEntity<?> getAllUser() {
//        List<User> allUser  = userService.getAllUser();
//        if(allUser!=null && !allUser.isEmpty())
//            return new ResponseEntity<>(allUser,HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }

//    //localhost:8080/user/arpan
//    @GetMapping("/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        User user = userService.getUserByUsername(username);
//        if (user != null)
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        else
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    //localhost:8080/user/
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.updateUser(username,updatedUser);
            return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //localhost:8080/user/delete
    @Transactional
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
                journalEntryService.deleteJournalEntriesOfUser(username);
                userService.deleteUser(username);
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }




}
