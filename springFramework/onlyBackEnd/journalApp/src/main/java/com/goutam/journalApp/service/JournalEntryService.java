package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    
    @Autowired
    private UserService userService;

    @Transactional
    public JournalEntry saveJournalEntryOfUser(JournalEntry journalEntry,String username) throws Exception {
        journalEntry.setDate(LocalDate.now());
        User user = userService.getUserByUsername(username);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
        return saved;
    }

    public List<JournalEntry> getAllJournalEntry() throws Exception{
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId id) throws Exception {
        return journalEntryRepository.findById(id).orElse(null);
    }

    public List<JournalEntry> getJournalEntriesOfUser(String username) throws Exception{
        User user = userService.getUserByUsername(username);
        return user.getJournalEntries();
    }

    public void deleteJournalEntryById(ObjectId id, String username) throws Exception {
        User user = userService.getUserByUsername(username);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);
    }

    public void deleteJournalEntriesOfUser(User user) throws Exception
    {
        List<JournalEntry> journalEntries = user.getJournalEntries();
        for(JournalEntry journalEntry:journalEntries)
        {
            journalEntryRepository.deleteById(journalEntry.getId());
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
