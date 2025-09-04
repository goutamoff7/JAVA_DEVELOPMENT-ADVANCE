package com.goutam.journalApp.service;

import com.goutam.journalApp.Pagination.PageResponse;
import com.goutam.journalApp.model.JournalEntry;
import com.goutam.journalApp.model.Roles;
import com.goutam.journalApp.model.User;
import com.goutam.journalApp.repository.JournalEntryRepository;
import com.goutam.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JournalEntryRepository journalEntryRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public User createNewAdmin(User user) {
        user.setUsername(userService.extractUsername(user));
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(Roles.ROLE_ADMIN);
        return userRepository.save(user);
    }

    @SuppressWarnings("unchecked")
    public <T> PageResponse<T> getAll(
            Class<T> entityClass,
            int pageNumber,
            int pageSize,
            String sortBy,
            String sortingOrder) {
        Sort sort = sortingOrder.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        if (entityClass.equals(User.class)) {
            Page<User> pageUsers = userRepository.findAll(pageable);
                return (PageResponse<T>) PageResponse.getPageResponse(pageUsers);
        } else if (entityClass.equals(JournalEntry.class)) {
            Page<JournalEntry> pageJournalEntries = journalEntryRepository.findAll(pageable);
            return (PageResponse<T>) PageResponse.getPageResponse(pageJournalEntries);
        } else {
            throw new IllegalArgumentException("Unsupported entity type: " + entityClass.getName());

        }
    }
}
