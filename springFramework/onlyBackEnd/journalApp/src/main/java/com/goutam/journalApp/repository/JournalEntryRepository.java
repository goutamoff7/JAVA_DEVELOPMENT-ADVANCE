package com.goutam.journalApp.repository;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
    Page<JournalEntry> findByUser(User user, Pageable pageable);
}
