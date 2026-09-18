package com.example.notesapp.repository;

import com.example.notesapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    // Search notes by title
    @Query("""
            SELECT n
            FROM Note n
            WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Note> searchByTitle(
            @Param("keyword") String keyword
    );


    // Search notes by title AND content
    @Query("""
            SELECT n
            FROM Note n
            WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            AND LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword2, '%'))
            """)
    List<Note> findByTitleContent(
            @Param("keyword") String keyword,
            @Param("keyword2") String keyword2
    );


    // Search notes between two dates
    @Query("""
            SELECT n
            FROM Note n
            WHERE n.createdAt >= :date
            AND n.createdAt <= :date2
            """)
    List<Note> searchByDateBetween(
            @Param("date") LocalDateTime date,
            @Param("date2") LocalDateTime date2
    );


    // Search notes by title OR content
    @Query("""
            SELECT n
            FROM Note n
            WHERE LOWER(n.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%'))
            """)
    List<Note> searchNotes(
            @Param("keyword") String keyword
    );


}