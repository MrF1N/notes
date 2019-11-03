package by.mrf1n.notes.controller;

import by.mrf1n.notes.model.NoteGroup;
import by.mrf1n.notes.repository.GroupRepository;
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
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping(value = "/groups")
    public List<NoteGroup> getAllGroups() {
        return groupRepository.findAll();
    }

    @PostMapping("/groups")
    public NoteGroup saveGroup(@RequestBody NoteGroup newGroup) {
        return groupRepository.save(newGroup);
    }

    @GetMapping("/groups/{id}")
    public NoteGroup getGroupById(@PathVariable
                                          BigInteger id) {
        return groupRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Group Not Found"));
    }


    @PutMapping("/groups/{id}")
    public NoteGroup updateGroup(@RequestBody NoteGroup newGroup, @PathVariable BigInteger id) {

        return groupRepository.findById(id).map(group -> {
            group.setName(newGroup.getName());
            return groupRepository.save(group);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Group Not Found"));
    }

    @DeleteMapping("/groups/{id}")
    public void deleteGroup(@PathVariable BigInteger id) {
        groupRepository.deleteById(id);
    }
}
