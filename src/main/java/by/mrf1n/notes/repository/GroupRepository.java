package by.mrf1n.notes.repository;

import by.mrf1n.notes.model.NoteGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<NoteGroup, BigInteger> {
    Optional<NoteGroup> findByName(String name);
}
