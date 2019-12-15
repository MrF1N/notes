package by.mrf1n.notes.repository;

import by.mrf1n.notes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий, который работатет с сообщениями, основная его функциональность генерируется средствами Spring,
 * здесь описывается только дополнительная функциональность, репозиторий будет связан с БД, которая настроена в application.properties
 */

public interface NoteRepository extends JpaRepository<Note, BigInteger> {
    List<Note> findByUserId(BigInteger userId);

    Optional<Note> findByIdAndUserId(BigInteger id, BigInteger userId);

    @Transactional
    void deleteByIdAndUserId(BigInteger id, BigInteger userId);
}
