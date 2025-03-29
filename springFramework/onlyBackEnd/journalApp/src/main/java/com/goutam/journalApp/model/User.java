package com.goutam.journalApp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "user")
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String username;

    @NotEmpty
    @Schema(description = "Password must be 8 character")
    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    @NotEmpty
    private String email;

    private Roles roles;

    @JsonIgnore
    @DBRef(lazy = true)
    private List<JournalEntry> journalEntries = new ArrayList<>();

}
