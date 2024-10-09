package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.User;
import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    //localhost:8080/admin/all-users
    @GetMapping("all-users")
    public ResponseEntity<?> getAllUser() {
        try{
            List<User> allUser  = adminService.getAllUser();
            if(allUser!=null && !allUser.isEmpty())
                return new ResponseEntity<>(allUser, HttpStatus.OK);
            else
                return new ResponseEntity<>("No User exits",HttpStatus.NO_CONTENT);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/admin/create-admin
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user) {
        try {
            if(user.getUsername()!=null && !user.getUsername().isEmpty() &&
                    user.getPassword()!=null && !user.getPassword().isEmpty())
                return new ResponseEntity<>(adminService.createNewAdmin(user), HttpStatus.CREATED);
            else
                throw new Exception();
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //localhost:8080/admin/all-journals
    @GetMapping("all-journals")
    public ResponseEntity<?> getAllJournals() {
        try{
            List<JournalEntry> allJournals  = adminService.getAllJournals();
            if(allJournals!=null && !allJournals.isEmpty())
                return new ResponseEntity<>(allJournals, HttpStatus.OK);
            else
                return new ResponseEntity<>("No Journal exits",HttpStatus.NO_CONTENT);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
