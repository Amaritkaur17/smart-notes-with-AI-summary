package com.example.notesapp.repository;

import com.example.notesapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/* Why extend JpaRepository<Note, Long>?
        Note → the entity this repository manages.
        Long → the type of the entity's primary key (id).

        By extending JpaRepository, you automatically get methods such as:
        save(note);
        findById(id);
        findAll();
        deleteById(id);
        existsById(id);
        count();
*/


public interface NoteRepository extends JpaRepository<Note,Long> {
}
