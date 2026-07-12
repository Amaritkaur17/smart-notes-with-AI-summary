package com.example.notesapp.controller;

import com.example.notesapp.dto.NoteRequest;
import com.example.notesapp.dto.NoteResponse;
import com.example.notesapp.entity.Note;
import com.example.notesapp.mapper.NoteMapper;
import com.example.notesapp.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;
    private final NoteMapper noteMapper;

    public NoteController(NoteService noteService, NoteMapper noteMapper) {
        this.noteService = noteService;
        this.noteMapper = noteMapper;
    }

    @PostMapping
    public NoteResponse createNote(@Valid @RequestBody NoteRequest noteRequest){

        Note note = noteMapper.toEntity(noteRequest);
        Note savedNote =noteService.createNote(note);
        return noteMapper.toResponse(savedNote);
    }
    @GetMapping
    public List<Note> getAllNotes(){
        return noteService.getAllNotes();
    }
    @GetMapping("/{id}")
    public Optional<Note> getNoteById(@PathVariable("id") Long id){
        return noteService.getNoteById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable("id") Long id, @RequestBody Note updatedNote){
        return noteService.updateNote(id,updatedNote);
    }
}
