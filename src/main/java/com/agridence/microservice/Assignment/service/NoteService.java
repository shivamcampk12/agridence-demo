package com.agridence.microservice.Assignment.service;

import com.agridence.microservice.Assignment.Vo.ResponseTemplateVO;
import com.agridence.microservice.Assignment.Repository.NoteRepository;
import com.agridence.microservice.Assignment.Entity.Note;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }
    public Optional<Note> findById(Long Id){
        return noteRepository.findById(Id);
    }

    public List<Note> findAll(){ return noteRepository.findAll(); }

    public List<Note> findAll(Long Id){
        List<Note> list = noteRepository.findAll();
        if(null != list){
            list.stream().filter(n->n.getUser().getId().equals(Id)).collect(Collectors.toList());
        }
        return list;
    }

    @Transactional
    public Integer deleteNoteByIdAndUser_Id(Long Id,Long userId){
        return noteRepository.deleteNoteByIdAndUser_Id(Id,userId);
    }
}