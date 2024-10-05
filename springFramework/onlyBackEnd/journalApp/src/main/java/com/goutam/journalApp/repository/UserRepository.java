package com.goutam.journalApp.repository;

import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    public User findUserByUsername(String username);
}
