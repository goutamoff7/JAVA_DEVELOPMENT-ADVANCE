package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private UserService userService;

    public JournalEntry saveJournalEntryOfUser(JournalEntry journalEntry,String username) {
        journalEntry.setDate(LocalDate.now());
        User user = userService.getUserByUsername(username);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
        return saved;
    }

    public List<JournalEntry> getAllJournalEntry() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId id) {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public void deleteJournalEntryById(ObjectId id, String username) throws Exception {
        try {
            User user = userService.getUserByUsername(username);
            user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }catch(Exception e)
        {
            throw new Exception();
        }
    }

    public JournalEntry updateJournalEntryById(ObjectId id, String username, JournalEntry updatedEntry) throws Exception {
        JournalEntry oldEntry = journalEntryRepository.findById(id).orElse(null);
        if(oldEntry!=null) {
            oldEntry.
                    setTitle(updatedEntry.getTitle() !=null && !updatedEntry.getTitle().equals("") ?
                            updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.
                    setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ?
                            updatedEntry.getContent() : oldEntry.getContent());

            return journalEntryRepository.save(oldEntry);
        }
        else
            throw new Exception();

    }
}
