package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.service.JournalEntryService;
import com.goutam.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    //localhost:8080/journal
    @PostMapping("/{username}")
    public ResponseEntity<JournalEntry> createJournalEntryOfUser(@PathVariable String username,
                                                                 @RequestBody JournalEntry journalEntry) {
        try {
            return new ResponseEntity<>(journalEntryService.saveJournalEntryOfUser(journalEntry, username), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    //localhost:8080/journal/goutam
    @GetMapping("/{username}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if(user!=null)
        {
            List<JournalEntry> allJournalEntryOfUser = user.getJournalEntries();
            if (allJournalEntryOfUser != null && !allJournalEntryOfUser.isEmpty())
                return new ResponseEntity<>(allJournalEntryOfUser, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    //localhost:8080/journal/id/1
    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId id) {
        JournalEntry journalEntry = journalEntryService.getJournalEntryById(id);
        if (journalEntry != null)
            return new ResponseEntity<>(journalEntry, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //localhost:8080/journal/goutam/1
    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId id, @PathVariable String username) {
        try {
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
