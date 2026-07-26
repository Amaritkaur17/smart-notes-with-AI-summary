package com.example.notesapp.controller;

import com.example.notesapp.dto.NoteRequest;
import com.example.notesapp.dto.NoteResponse;
import com.example.notesapp.entity.Note;
import com.example.notesapp.mapper.NoteMapper;
import com.example.notesapp.service.NoteService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public NoteResponse createNote(@Valid @RequestBody NoteRequest noteRequest) {

        Note note = noteMapper.toEntity(noteRequest);
        Note savedNote = noteService.createNote(note);
        return noteMapper.toResponse(savedNote);
    }

    @GetMapping
    public Page<NoteResponse> getAllNotes(@ParameterObject Pageable pageable) {
        Page<Note> notePage = noteService.getAllNotes(pageable);
        return notePage.map(noteMapper::toResponse);
    }

    @GetMapping("/{id}")
    public NoteResponse getNoteById(@PathVariable("id") Long id) {
        Note note = noteService.getNoteById(id);
        return noteMapper.toResponse(note);
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable("id") Long id) {
        noteService.deleteNote(id);
    }

    @PutMapping("/{id}")
    public NoteResponse updateNote(@PathVariable("id") Long id, @RequestBody NoteRequest updatedNote) {
        Note note = noteMapper.toEntity(updatedNote);
        Note noteUpdated = noteService.updateNote(id, note);
        return noteMapper.toResponse(noteUpdated);
    }

    @GetMapping("/search")
    public List<NoteResponse> searchNotes(@RequestParam String keyword) {
        List<Note> noteListSearched = noteService.searchNoteByTitle(keyword);
        return noteMapper.toResponseList(noteListSearched);
    }
    @GetMapping("/search-by-title-and-content")
    public List<NoteResponse> searchByTitleAndContent(@RequestParam String keyword){
        List<Note> noteList = noteService.searchByTitleAndContent(keyword);
        return noteMapper.toResponseList(noteList);
    }
}

