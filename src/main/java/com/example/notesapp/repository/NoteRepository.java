package com.example.notesapp.repository;

import com.example.notesapp.entity.Note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;



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
     @Query("""
              SELECT n 
             FROM Note n
             WHERE LOWER(n.title) LIKE LOWER(CONCAT('%',:keyword,'%'))
             """)
    List<Note>SearchByTitle(@Param("keyword")String title);

    List<Note> findByTitleContainingIgnoreCaseAndContentContainingIgnoreCase(String title,String content);



}
