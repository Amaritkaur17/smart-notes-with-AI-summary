package com.example.notesapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteRequest {
    @NotBlank(message = "Title cannot be blank")
    @Size(max=100, message = "Title cannot exceed 100 characters")
    private String title;

    @NotBlank(message = "Content cannot be blank")
    @Size(max=500, message = "Content cannot exceed 500 characters")
    private String content;

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return content;
    }

    public void setContent(String content){
        this.content=content;
    }
}
