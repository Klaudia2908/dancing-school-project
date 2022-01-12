package pl.klaudiajastrzebska.dancingschool.catalog.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "OSOBY")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "IMIE")
    private String firstName;
    @Column(name = "NAZWISKO")
    private String lastName;
    @Column(name = "DATA_URODZENIA")
    private LocalDate birthDate;
    @Column(name = "PLEC")
    private String gender;
    @Column(name = "OPIS")
    private String description;
    @Column(name = "ID_USER")
    private long userId;

    @ManyToOne
    @JoinColumn(name = "ID_TYP_OSOBY", referencedColumnName = "ID")
    private PersonTypeEntity personType;
}
