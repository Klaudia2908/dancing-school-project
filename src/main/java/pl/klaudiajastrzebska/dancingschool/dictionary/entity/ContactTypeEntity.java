package pl.klaudiajastrzebska.dancingschool.dictionary.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_KONTAKT_TYP")
public class ContactTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAZWA")
    private String value;
}
