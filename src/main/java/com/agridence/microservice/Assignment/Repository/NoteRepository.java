package com.agridence.microservice.Assignment.Repository;

import com.agridence.microservice.Assignment.Entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NoteRepository  extends JpaRepository<Note,Long> {
    Optional<Note> findById(Long Id);
    Note save(Note note);

    List<Note> findAll();

    Integer deleteNoteByIdAndUser_Id(Long Id,Long userId);
}
