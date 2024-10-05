package com.goutam.journalApp.service;

import com.goutam.journalApp.model.JournalEntry;
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

    public JournalEntry createEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDate.now());
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntry() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId myId) {
        return journalEntryRepository.findById(myId).orElse(null);
    }

    public void deleteJournalEntryById(ObjectId myId) {
        journalEntryRepository.deleteById(myId);
    }

    public JournalEntry updateJournalEntryById(ObjectId id, JournalEntry updatedEntry) {
        JournalEntry oldEntry = journalEntryRepository.findById(id).orElse(null);
        if(oldEntry!=null) {
            oldEntry.
                    setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ?
                            updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.
                    setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ?
                            updatedEntry.getContent() : oldEntry.getContent());
        }
            return journalEntryRepository.save(oldEntry);

    }
}
