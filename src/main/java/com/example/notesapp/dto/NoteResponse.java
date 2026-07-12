package com.example.notesapp.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class NoteResponse  {

    private long id;

    private String title;


    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;



    public long getId(){
        return id;
    }

    @NotBlank
    @Size(max = 100)
    public String getTitle(){
        return title;
    }

    @NotBlank
    @Size(max = 500)
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

