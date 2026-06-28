package com.example.notesapp.controller;

import com.example.notesapp.entity.Note;
import com.example.notesapp.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@RequestBody Note note){
        return noteService.createNote(note);
    }
    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }
    @GetMapping("/{id}")
    public Optional<Note> getNoteById(@PathVariable Long id){
        return noteService.getNoteById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
    }
}
