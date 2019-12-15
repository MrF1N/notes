package by.mrf1n.notes.validation;

import by.mrf1n.notes.controller.GroupController;
import by.mrf1n.notes.controller.UserController;
import by.mrf1n.notes.model.Note;
import by.mrf1n.notes.model.NoteGroup;
import by.mrf1n.notes.model.User;
import by.mrf1n.notes.repository.GroupRepository;
import by.mrf1n.notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис для описания сложной логики, которая будет использоваться многократно в коде, чтобы не загромождать
 */

@Service
public class HelpService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final UserController userController;
    private final GroupController groupController;

    @Autowired
    public HelpService(GroupRepository groupRepository, UserRepository userRepository, UserController userController, GroupController groupController) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.userController = userController;
        this.groupController = groupController;
    }

    public boolean validateGroup(@ModelAttribute("group") NoteGroup group, BindingResult result) {
        boolean error = false;
        Optional<NoteGroup> baseGroup = groupRepository.findByName(group.getName());
        if (group.getName().isEmpty()) {
            result.rejectValue("name", "error.name.empty");
            error = true;
        } else if (baseGroup.isPresent() && !baseGroup.get().getId().equals(group.getId())) {
            result.rejectValue("name", "error.name.same");
            error = true;
        }

        return error;
    }

    public boolean validateUser(@ModelAttribute("user") User user, BindingResult result) {
        boolean error = false;
        Optional<User> baseUser = userRepository.findByLogin(user.getLogin());

        if (user.getLogin().isEmpty()) {
            result.rejectValue("login", "error.login.empty");
            error = true;
        } else if (baseUser.isPresent() && !baseUser.get().getId().equals(user.getId())) {
            result.rejectValue("login", "error.login.same");
            error = true;
        }

        if (user.getPassword().isEmpty()) {
            result.rejectValue("password", "error.password.empty");
            error = true;
        }

        if (user.getRoleId() == null) {
            result.rejectValue("roleId", "error.roleId.empty");
            error = true;
        }

        return error;
    }

    public boolean validateNote(@ModelAttribute("note") Note note, BindingResult result) {
        boolean error = false;
        if (note.getMessage().isEmpty()) {
            result.rejectValue("message", "error.message.empty");
            error = true;
        }

        return error;
    }

    public void fillNoteModel(ModelMap model) {
        List<User> allUsers = userController.getAllUsers();
        model.addAttribute("users", allUsers
                .stream().collect(Collectors.toMap(User::getId, User::getLogin)));
        List<NoteGroup> allGroups = groupController.getAllGroups();
        model.addAttribute("groups", allGroups
                .stream().collect(Collectors.toMap(NoteGroup::getId, NoteGroup::getName)));
    }
}
