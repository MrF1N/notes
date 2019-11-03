package by.mrf1n.notes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "notes")
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequence_note")
    @TableGenerator(name="sequence_note", table = "id_gen", pkColumnName="gen_name", valueColumnName="gen_value", allocationSize=1000)
    @Column(name = "note_id")
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
    private BigInteger userId;

    @Column(name = "message")
    private String message;

    @ManyToOne
    @JoinColumn(name = "group_id", insertable = false, updatable = false)
    private NoteGroup noteGroup;

    @Column(name = "group_id", nullable = false)
    private BigInteger noteGroupId;

    public Note() {

    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getUserId() {
        return userId;
    }

    public void setUserId(BigInteger userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NoteGroup getNoteGroup() {
        return noteGroup;
    }

    public void setNoteGroup(NoteGroup noteGroup) {
        this.noteGroup = noteGroup;
    }

    public BigInteger getNoteGroupId() {
        return noteGroupId;
    }

    public void setNoteGroupId(BigInteger noteGroupId) {
        this.noteGroupId = noteGroupId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", user=" + user +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", noteGroup=" + noteGroup +
                ", noteGroupId=" + noteGroupId +
                '}';
    }
}
