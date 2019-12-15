package by.mrf1n.notes.repository;

import by.mrf1n.notes.model.NoteGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

/**
 * Репозиторий, который работатет с группами сообщений, основная его функциональность генерируется средствами Spring,
 * здесь описывается только дополнительная функциональность, репозиторий будет связан с БД, которая настроена в application.properties
 */

public interface GroupRepository extends JpaRepository<NoteGroup, BigInteger> {
    Optional<NoteGroup> findByName(String name);
}
