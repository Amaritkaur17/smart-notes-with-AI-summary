package com.example.notesapp.service;

import com.example.notesapp.entity.Note;
import com.example.notesapp.entity.User;
import com.example.notesapp.exception.ResourceNotFoundException;
import com.example.notesapp.repository.NoteRepository;
import com.example.notesapp.repository.UserRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service

public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRespository userRespository;
    private static final Logger logger =LoggerFactory.getLogger(NoteService.class);

    public NoteService(NoteRepository noteRepository, UserRespository userRespository){
        this.noteRepository = noteRepository;
        this.userRespository = userRespository;
    }

    public Note createNote(Note note){
        User owner = userRespository.findByUsername(getCurrentUsername()).orElseThrow(()-> new ResourceNotFoundException("User not found"));
        note.setOwner(owner);
        logger.info("Creating a new not with title : {}",note.getTitle());
        note.setCreatedAt(LocalDateTime.now());
        note.setUpdatedAT(LocalDateTime.now());
        Note savedNote =  noteRepository.save(note) ;
        logger.info("Note created sucessfully with id : {}",savedNote.getId());
        return savedNote;
    }

    public Page<Note> getAllNotes(Pageable pageable){

        return noteRepository.findAllByOwner_Username(getCurrentUsername(),pageable);
    }

    public Note getNoteById(Long id){
        logger.warn("Fetching note with id: {}", id);
        String username = getCurrentUsername();
        return noteRepository.findByIdAndOwner_Username(id,username)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Note not found with ID : " + id));
        }

    public void deleteNote(Long id){
        Note note = getNoteById(id);
        noteRepository.delete(note);
    }
    public Note updateNote(Long id,Note updatedNote){
       Note note = getNoteById(id);

       note.setTitle(updatedNote.getTitle());
       note.setContent(updatedNote.getContent());
       note.setUpdatedAT(LocalDateTime.now());

       return noteRepository.save(note);
        }


    public List<Note> searchNoteByTitle(String keyword){
       return noteRepository.searchByTitle(getCurrentUsername(),keyword);
    }

    public List<Note> searchByTitleAndContent(String Keyword){
        return noteRepository.findByTitleContent(getCurrentUsername(),Keyword,Keyword);
    }

    private String getCurrentUsername(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
    }
}
