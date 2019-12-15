package by.mrf1n.notes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * DTO (Data Transfer Object) группы сообщений со всеми настройками автогенерации таблиц в БД
 */

@Entity
@Table(name = "note_groups")
public class NoteGroup implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence_group")
    @TableGenerator(name="sequence_group", table = "id_gen", pkColumnName="gen_name", valueColumnName="gen_value", allocationSize=1000)
    @Column(name = "group_id")
    private BigInteger id;
    @Column(name = "group_name")
    private String name;

    @JsonIgnore
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    @JoinColumn(name = "group_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "noteGroup", cascade = CascadeType.ALL)
    private List<Note> noteList;

    public NoteGroup() {

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public String toString() {
        return "NoteGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
