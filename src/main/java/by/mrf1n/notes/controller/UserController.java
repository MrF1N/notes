package by.mrf1n.notes.controller;

import by.mrf1n.notes.model.User;
import by.mrf1n.notes.repository.UserRepository;
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
 * Контроллер для работы с юзерами (поддерживает все CRUD операции)
 */

@RestController
@RequestMapping(path = "/notes/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/users")
    public User saveUser(@RequestBody User newUser) {
        return userRepository.save(newUser);
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable
                                    BigInteger id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found"));
    }


    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable BigInteger id) {

        return userRepository.findById(id).map(user -> {
            user.setRoleId(newUser.getRoleId());
            user.setLogin(newUser.getLogin());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User Not Found"));
//                .orElseGet(() -> {
//            newUser.setId(id);
//            return userRepository.save(newUser);
//        });
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable BigInteger id) {
        userRepository.deleteById(id);
    }
}
