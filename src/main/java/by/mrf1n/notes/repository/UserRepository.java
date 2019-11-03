package by.mrf1n.notes.repository;

import by.mrf1n.notes.model.NoteGroup;
import by.mrf1n.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, BigInteger> {
    Optional<User> findByLogin(String login);
}
