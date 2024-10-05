package com.goutam.journalApp.controller;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    //localhost:8080/journal
    @GetMapping
    public List<JournalEntry> getAllJournalEntry() {
        return journalEntryService.getAllJournalEntry();
    }

    //localhost:8080/journal
    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry) {
        return journalEntryService.createEntry(journalEntry);
    }

    //localhost:8080/journal/id/1
    @GetMapping("/id/{id}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId id) {
        return journalEntryService.getJournalEntryById(id);
    }

    //localhost:8080/journal/id/1
    @DeleteMapping("/id/{id}")
    public void deleteById(@PathVariable ObjectId id) {
        journalEntryService.deleteJournalEntryById(id);
    }

    //localhost:8080/journal/id/1
    @PutMapping("/id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id,
                                               @RequestBody JournalEntry updatedEntry) {
        return journalEntryService.updateJournalEntryById(id,updatedEntry);
    }

}
