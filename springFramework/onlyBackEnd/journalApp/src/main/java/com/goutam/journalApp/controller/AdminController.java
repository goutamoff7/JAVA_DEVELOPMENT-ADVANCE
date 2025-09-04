package com.goutam.journalApp.controller;

import com.goutam.journalApp.Pagination.PageResponse;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.DTO.UserDTO;
import com.goutam.journalApp.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin APIs", description = "Create admin, Read all users and Journals,")
public class AdminController {

    @Autowired
    AdminService adminService;

//    localhost:8080/admin/all-users?
//    pageNumber=0&
//    pageSize=12&
//    sortBy=id&
//    sortingOrder=ASC
    @Operation(summary = "Get all User details")
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser(
            @RequestParam(required = false,defaultValue = "0") int pageNumber,
            @RequestParam(required = false,defaultValue = "12") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "ASC") String sortingOrder) {
        try {
            PageResponse<User> allUser = adminService.getAll(
                    User.class,pageNumber,pageSize,sortBy,sortingOrder);
            if (allUser != null)
                return new ResponseEntity<>(allUser, HttpStatus.OK);
            else
                return new ResponseEntity<>("No User exits", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/admin/create-admin
    @Operation(summary = "Signup as an Admin")
    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdmin(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                User user = new User();
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());
                return new ResponseEntity<>(adminService.createNewAdmin(user), HttpStatus.CREATED);
            } else
                return new ResponseEntity<>("Admin Registration Failed!!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
//    localhost:8080/admin/all-journals?
//    pageNumber=0&
//    pageSize=12&
//    sortBy=id&
//    sortingOrder=ASC
    @Operation(summary = "Get all Journal entries")
    @GetMapping("all-journals")
    public ResponseEntity<?> getAllJournals(
            @RequestParam(required = false,defaultValue = "0") int pageNumber,
            @RequestParam(required = false,defaultValue = "12") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "ASC") String sortingOrder){
        try {
            PageResponse<JournalEntry> allJournals = adminService.getAll(
                    JournalEntry.class,pageNumber,pageSize,sortBy,sortingOrder);
            if (allJournals != null)
                return new ResponseEntity<>(allJournals, HttpStatus.OK);
            else
                return new ResponseEntity<>("No Journal exits", HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
