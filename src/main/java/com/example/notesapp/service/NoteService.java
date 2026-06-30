package com.example.notesapp.service;

import com.example.notesapp.entity.Note;
import com.example.notesapp.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note){
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAT(LocalDateTime.now());
        return noteRepository.save(note);
    }

    public List<Note> getAllNotes(){
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id){
        return noteRepository.findById(id);
    }

    public void deleteNote(Long id){
        noteRepository.deleteById(id);
    }
    public Note updateNote(Long id,Note updatedNote){
        Optional<Note> exsistingNote = noteRepository.findById(id);
        if(exsistingNote.isPresent()){
            Note note = exsistingNote.get();
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setUpdatedAT(LocalDateTime.now());
            return noteRepository.save(note);
        }
        return null;
    }
}
