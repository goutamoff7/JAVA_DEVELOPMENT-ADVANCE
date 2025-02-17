package com.goutam.journalApp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document(collection = "journal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JournalEntry implements Serializable {
    @Id
    private ObjectId id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private LocalDate date;
}
