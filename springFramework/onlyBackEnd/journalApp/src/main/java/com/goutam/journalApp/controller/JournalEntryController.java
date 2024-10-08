package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.service.JournalEntryService;
import com.goutam.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //localhost:8080/journal
    @PostMapping()
    public ResponseEntity<JournalEntry> createJournalEntryOfUser(@RequestBody JournalEntry journalEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            return new ResponseEntity<>(journalEntryService.saveJournalEntryOfUser(journalEntry, username), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //localhost:8080/journal
    @GetMapping()
    public ResponseEntity<?> getAllJournalEntriesOfUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<JournalEntry> allJournalEntryOfUser = journalEntryService.getJournalEntriesOfUser(username);
            if (allJournalEntryOfUser != null && !allJournalEntryOfUser.isEmpty())
                return new ResponseEntity<>(allJournalEntryOfUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //localhost:8080/journal/id/1
    @GetMapping("/id/{searchedId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId searchedId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<JournalEntry> collect = journalEntryService.verifyJournalId(username, searchedId);
            if (!collect.isEmpty()) {
                JournalEntry journalEntry = journalEntryService.getJournalEntryById(searchedId);
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            } else
                return new ResponseEntity<>("Journal Entry not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //localhost:8080/journal/id/1
    @PutMapping("/id/{givenId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId givenId,
                                                    @RequestBody JournalEntry updatedEntry) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.getUserByUsername(username);
            List<JournalEntry> collect = journalEntryService.verifyJournalId(username, givenId);
            if (!collect.isEmpty()) {
                JournalEntry journalEntry = journalEntryService.updateJournalEntryById(givenId, updatedEntry);
                return new ResponseEntity<>(journalEntry, HttpStatus.ACCEPTED);
            } else
                return new ResponseEntity<>("Journal Entry not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //localhost:8080/journal/id/1
    @DeleteMapping("/id/{givenId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId givenId) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            boolean removed = journalEntryService.deleteJournalEntryById(givenId, username);
            if (removed)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>("Journal Entry not found",HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
