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
    public ResponseEntity<JournalEntry> createJournalEntryOfUser(@RequestBody JournalEntry journalEntry)
    {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            return new ResponseEntity<>(journalEntryService.saveJournalEntryOfUser(journalEntry, username), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //localhost:8080/journal/goutam
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
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //localhost:8080/journal/id/1
    @GetMapping("/id/{searchedId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId searchedId) {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userService.getUserByUsername(username);
            List<JournalEntry> collect = user
                            .getJournalEntries()
                            .stream()
                            .filter(x -> x.getId().equals(searchedId))
                            .toList();
            if (!collect.isEmpty()) {
                JournalEntry journalEntry = journalEntryService.getJournalEntryById(searchedId);
                return new ResponseEntity<>(journalEntry, HttpStatus.OK);
            }
            else
                throw new Exception();
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //localhost:8080/journal/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            journalEntryService.deleteJournalEntryById(id,username);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //localhost:8080/journal/id/1
    @PutMapping("/{username}/{id}")
    public ResponseEntity<JournalEntry> updateJournalEntryById(@PathVariable ObjectId id,
                                                               @RequestBody JournalEntry updatedEntry,
                                                                @PathVariable String username) {
        try {
            JournalEntry journalEntry = journalEntryService.updateJournalEntryById(id,username,updatedEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
