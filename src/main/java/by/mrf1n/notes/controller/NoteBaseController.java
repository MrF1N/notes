package by.mrf1n.notes.controller;

import by.mrf1n.notes.model.Note;
import by.mrf1n.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "/notes/api")
public class NoteBaseController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @PostMapping("/notes")
    public Note saveNote(@RequestBody Note newNote) {
        return noteRepository.save(newNote);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable BigInteger id) {
        return noteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note Not Found"));
    }


    @PutMapping("/notes/{id}")
    public Note updateNote(@RequestBody Note newNote, @PathVariable BigInteger id) {

        return noteRepository.findById(id).map(note -> {
            note.setMessage(newNote.getMessage());
            note.setNoteGroupId(newNote.getNoteGroupId());
            note.setUserId(newNote.getUserId());
            return noteRepository.save(note);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note Not Found"));
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote( @PathVariable BigInteger id) {
        noteRepository.deleteById(id);
    }
}
