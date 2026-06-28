package com.example.notesapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

// entity annotation allows JPA to create table in DB with the name same as class name
// for the custom table name : @Table(name = "notes")
@Entity
public class Note {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto generated ids
    private long id;

    private String title;

    @Column(columnDefinition = "TEXT") // customises column mapping
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAT;

}
