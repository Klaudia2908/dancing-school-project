package pl.klaudiajastrzebska.dancingschool.catalog.person.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.ContactTypeEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "KONTAKT_OSOBY")
public class PersonContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_S_TYP_KONTAKTU", referencedColumnName = "ID")
    private ContactTypeEntity contactType;

    @ManyToOne
    @JoinColumn(name = "ID_OSOBY", referencedColumnName = "ID")
    private PersonEntity person;

    @Column(name = "WARTOSC")
    private String value;
}
