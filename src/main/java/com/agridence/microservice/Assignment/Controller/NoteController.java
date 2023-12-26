package com.agridence.microservice.Assignment.Controller;

import com.agridence.microservice.Assignment.Entity.Note;
import com.agridence.microservice.Assignment.Entity.User;
import com.agridence.microservice.Assignment.Vo.NoteRequest;
import com.agridence.microservice.Assignment.service.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private final NoteService noteService;
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> saveNote(@RequestBody NoteRequest noteRequest) {

        Note note = new Note(null,noteRequest.getTitle(),noteRequest.getDescription(),new User(noteRequest.getUserId(),null,null,null,null));

        Note savedNote = noteService.saveNote(note);

        return new ResponseEntity<>(savedNote, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNote(@RequestBody NoteRequest noteRequest, @PathVariable("id") Long Id) {

        Optional<Note> savedNote = noteService.findById(Id);

        Note newNote = savedNote.orElse(null);

        if(null != newNote){
            newNote.setTitle(noteRequest.getTitle());
            newNote.setDescription(noteRequest.getDescription());
            Note updatedNote = noteService.saveNote(newNote);

            return new ResponseEntity<>(updatedNote, HttpStatus.OK);
        }
        return new ResponseEntity<>("Invalid Note Id", HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{Id}/{userId}")
    public ResponseEntity<?> deleteNoteById(@PathVariable("Id") Long Id,@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(noteService.deleteNoteByIdAndUser_Id(Id,userId).toString()+" << Notes Deleted!", HttpStatus.OK);
    }

    @GetMapping("/findall")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(noteService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/findall/{userId}")
    public ResponseEntity<?> findAll(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(noteService.findAll(userId), HttpStatus.OK);
    }
}
