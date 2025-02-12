package com.goutam.journalApp.controller;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.DTO.UserDTO;
import com.goutam.journalApp.service.JournalEntryService;
import com.goutam.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Create, Read, Update and Delete User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JournalEntryService journalEntryService;

    //localhost:8080/user/get
    @Operation(summary="Get User Information")
    @GetMapping("/get")
    public ResponseEntity<?> getUser() {
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.getUserByUsername(username);
            if (user!=null)
                return new ResponseEntity<>(user, HttpStatus.OK);
            else
                return new ResponseEntity<>("No User exits",HttpStatus.NO_CONTENT);
        }catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/user/update
    @Operation(summary="Update User Information")
    @PutMapping("/update")
    public ResponseEntity<?> updateUserPassword(@RequestBody UserDTO userDTO) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            String password = userDTO.getPassword();
            userService.updateUserPassword(username,password);
            return new ResponseEntity<>("Password changed successfully",HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    //localhost:8080/user/delete
    @Operation(summary="Delete User")
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
