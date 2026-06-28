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
    private LocalDateTime updatedAt;

    public Note() {
    }

    public long getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public LocalDateTime getCreatedAt(){
        return createdAt;
    }

    public LocalDateTime getUpdatedAT(){
        return updatedAt;
    }

    public void setId(Long id){
        this.id = id;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContent(String content){
        this.content = content;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt =createdAt;
    }
    public void setUpdatedAT(LocalDateTime updatedAT){
        this.updatedAt = updatedAT;
    }

}
