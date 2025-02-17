package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
//    @CachePut(value="journalEntry",key="#result.id.toString()")
    public JournalEntry saveJournalEntryOfUser(JournalEntry journalEntry,String username) throws Exception {
        journalEntry.setDate(LocalDate.now());
        User user = userService.getUserByUsername(username);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
        return saved;
    }

//    @Cacheable(value="journalEntry",key="#id.toString()")
    public JournalEntry getJournalEntryById(ObjectId id) throws Exception {
        return journalEntryRepository.findById(id).orElse(null);
    }

//    @Cacheable(value = "journalEntries", key = "#username")
    public List<JournalEntry> getJournalEntriesOfUser(String username) throws Exception{
        User user = userService.getUserByUsername(username);
        return user.getJournalEntries();
    }

    //verify whether the given id belongs to the requested user or not
    public List<JournalEntry> verifyJournalId(String username,ObjectId id) throws Exception
    {
        User user = userService.getUserByUsername(username);
        return  user
                .getJournalEntries()
                .stream()
                .filter(x -> x.getId().equals(id))
                .toList();
    }

    @Transactional
//    @CacheEvict(value="journalEntry",key="#id.toString()")
    public boolean deleteJournalEntryById(ObjectId id, String username) throws Exception {
        boolean removed=false;
        User user = userService.getUserByUsername(username);
        removed =  user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        if(removed)
        {
            userService.saveUser(user);
            journalEntryRepository.deleteById(id);
        }
        return removed;
    }

//    @CacheEvict(value="journalEntry",key="#id.toString()")
    public void deleteJournalEntriesOfUser(String username) throws Exception
    {
        User user = userService.getUserByUsername(username);
        List<JournalEntry> journalEntries = user.getJournalEntries();
          journalEntries.forEach(x-> {
              journalEntryRepository.deleteById(x.getId());
              evictJournalEntryFromCache(x.getId());
          });
    }

    // This method is used to evict specific journal entries from cache
//    @CacheEvict(value = "journalEntry", key = "#id.toString()")
    public void evictJournalEntryFromCache(ObjectId id) {
    }

//    @CachePut(value = "journalEntry", key = "#id.toString()")
    public JournalEntry updateJournalEntryById(ObjectId id,JournalEntry updatedEntry) throws Exception {
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
