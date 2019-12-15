package by.mrf1n.notes.repository;

import by.mrf1n.notes.model.NoteGroup;
import by.mrf1n.notes.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Репозиторий, который работатет с юзерами, основная его функциональность генерируется средствами Spring,
 * здесь описывается только дополнительная функциональность, репозиторий будет связан с БД, которая настроена в application.properties
 */

public interface UserRepository extends JpaRepository<User, BigInteger> {
    Optional<User> findByLogin(String login);
}
