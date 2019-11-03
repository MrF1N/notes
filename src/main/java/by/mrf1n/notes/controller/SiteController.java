package by.mrf1n.notes.controller;

import by.mrf1n.notes.model.Note;
import by.mrf1n.notes.model.NoteGroup;
import by.mrf1n.notes.model.User;
import by.mrf1n.notes.validation.HelpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;

@Controller
@RequestMapping(path = "/notes")
public class SiteController {

    private final GroupController groupController;
    private final UserController userController;
    private final NoteBaseController noteController;
    private final HelpService helpService;

    @Autowired
    public SiteController(GroupController groupController, UserController userController, NoteBaseController noteController, HelpService helpService) {
        this.groupController = groupController;
        this.userController = userController;
        this.noteController = noteController;
        this.helpService = helpService;
    }

    @RequestMapping("")
    public String getMainPage() {
        return "index";
    }

//    @RequestMapping("/test")
//    public String getTestPage(ModelMap model) {
//        model.addAttribute("listPersons", userRepository.findAll());
//        return "index1";
//    }

    @RequestMapping("/users")
    public String getUsersPage(ModelMap model) {
        model.addAttribute("listUsers", userController.getAllUsers());
        return "users/usersPage";
    }

    @RequestMapping("/users/user/new")
    public String addUserPage(ModelMap model) {
        model.addAttribute("user", new User());
        return "users/user";
    }

    @RequestMapping("/users/user/{id}")
    public String updateUserPage(ModelMap model, @PathVariable BigInteger id) {
        model.addAttribute("user", userController.getUserById(id));
        return "users/user";
    }

    @RequestMapping("/users/user/add")
    public String addNewUser(@ModelAttribute("user") User user, BindingResult result) {
        if (helpService.validateUser(user, result)) return "users/user";
        userController.saveUser(user);
        return "redirect:/notes/users";
    }

    @RequestMapping("/users/user/edit")
    public String editUser(@ModelAttribute("user") User user, BindingResult result) {
        if (helpService.validateUser(user, result)) return "users/user";
        userController.updateUser(user, user.getId());
        return "redirect:/notes/users";
    }

    @RequestMapping("/users/user/delete/{id}")
    public String deleteUser(@PathVariable BigInteger id) {
        userController.deleteUser(id);
        return "redirect:/notes/users";
    }

    @RequestMapping("/notes")
    public String getNotesPage(ModelMap model) {
        model.addAttribute("listNotes", noteController.getAllNotes());
        return "notes/notesPage";
    }

    @RequestMapping("/notes/note/new")
    public String addNotePage(ModelMap model) {
        helpService.fillNoteModel(model);
        model.addAttribute("note", new Note());
        return "notes/note";
    }

    @RequestMapping("/notes/note/{id}")
    public String updateNotePage(ModelMap model, @PathVariable BigInteger id) {
        helpService.fillNoteModel(model);
        model.addAttribute("note", noteController.getNoteById(id));
        return "notes/note";
    }

    @RequestMapping("/notes/note/add")
    public String addNewNote(@ModelAttribute("note") Note note, BindingResult result, ModelMap model) {
        if (helpService.validateNote(note, result)) {
            helpService.fillNoteModel(model);
            return "notes/note";
        }
        noteController.saveNote(note);
        return "redirect:/notes/notes";
    }

    @RequestMapping("/notes/note/edit")
    public String editNote(@ModelAttribute("note") Note note, BindingResult result, ModelMap model) {
        if (helpService.validateNote(note, result)) {
            helpService.fillNoteModel(model);
            return "notes/note";
        }
        noteController.updateNote(note, note.getId());
        return "redirect:/notes/notes";
    }

    @RequestMapping("/notes/note/delete/{id}")
    public String deleteNote(@PathVariable BigInteger id) {
        noteController.deleteNote(id);
        return "redirect:/notes/notes";
    }

    @RequestMapping("/groups")
    public String getGroupsPage(ModelMap model) {
        model.addAttribute("listGroups", groupController.getAllGroups());
        return "groups/groupsPage";
    }

    @RequestMapping("/groups/group/new")
    public String addGroupPage(ModelMap model) {
        model.addAttribute("group", new NoteGroup());
        return "groups/group";
    }

    @RequestMapping("/groups/group/{id}")
    public String updateGroupPage(ModelMap model, @PathVariable BigInteger id) {
        model.addAttribute("group", groupController.getGroupById(id));
        return "groups/group";
    }

    @RequestMapping("/groups/group/add")
    public String addNewGroup(@ModelAttribute("group") NoteGroup group, BindingResult result) {
        if (helpService.validateGroup(group, result)) return "groups/group";
        groupController.saveGroup(group);
        return "redirect:/notes/groups";
    }

    @RequestMapping("/groups/group/edit")
    public String editGroup(@ModelAttribute("group") NoteGroup group, BindingResult result) {
        if (helpService.validateGroup(group, result)) return "groups/group";
        groupController.updateGroup(group, group.getId());
        return "redirect:/notes/groups";
    }

    @RequestMapping("/groups/group/delete/{id}")
    public String deleteGroup(@PathVariable BigInteger id) {
        groupController.deleteGroup(id);
        return "redirect:/notes/groups";
    }
}
