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

/**
 * Контроллер для работы с сообщениями конкретного пользователя сообщений (поддерживает все CRUD операции)
 */

@RestController
@RequestMapping(path = "/notes/api/users/{userId}")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @GetMapping(value = "/notes")
    public List<Note> getAllNotesByUserId(@PathVariable(name = "userId") BigInteger userId) {
        return noteRepository.findByUserId(userId);
    }

    @PostMapping("/notes")
    public Note saveNote(@PathVariable(name = "userId") BigInteger userId, @RequestBody Note newNote) {
        newNote.setUserId(userId);
        return noteRepository.save(newNote);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(name = "userId") BigInteger userId, @PathVariable
                                    BigInteger id) {
        return noteRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note Not Found"));
    }


    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(name = "userId") BigInteger userId,
                           @RequestBody Note newNote, @PathVariable BigInteger id) {

        return noteRepository.findByIdAndUserId(id, userId).map(note -> {
            note.setMessage(newNote.getMessage());
            note.setNoteGroupId(newNote.getNoteGroupId());
            note.setUserId(userId);
            return noteRepository.save(note);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Note Not Found"));
    }

    @DeleteMapping("/notes/{id}")
    public void deleteNote(@PathVariable(name = "userId") BigInteger userId, @PathVariable BigInteger id) {
        noteRepository.deleteByIdAndUserId(id, userId);
    }
}
