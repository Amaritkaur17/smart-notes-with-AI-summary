package com.example.notesapp.service;

import com.example.notesapp.entity.Note;
import com.example.notesapp.exception.ResourceNotFoundException;
import com.example.notesapp.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class NoteService {

    private final NoteRepository noteRepository;
    private static final Logger logger =LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    public Note createNote(Note note){
        logger.info("Creating a new not with title : {}",note.getTitle());
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAT(LocalDateTime.now());
        Note savedNote =  noteRepository.save(note) ;
        logger.info("Note created sucessfully with id : {}",savedNote.getId());
        return savedNote;
    }

    public Page<Note> getAllNotes(Pageable pageable){
        return noteRepository.findAll(pageable);
    }

    public Note getNoteById(Long id){
        logger.warn("Fetching note with id: {}", id);
        return noteRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Note not found with ID : " + id));
        }

    public void deleteNote(Long id){
        logger.info("Deleted the note with id : {}", id) ;
        noteRepository.deleteById(id);
    }
    public Note updateNote(Long id,Note updatedNote){
        Optional<Note> exsistingNote = noteRepository.findById(id);
        if(exsistingNote.isPresent()){
            Note note = exsistingNote.get();
            note.setTitle(updatedNote.getTitle());
            note.setContent(updatedNote.getContent());
            note.setUpdatedAT(LocalDateTime.now());
            logger.info("Updating note with id : {}", note.getId());
            return noteRepository.save(note);
        }

        //return null;
        throw new ResourceNotFoundException("Note not found with ID :"+id);
    }
}
