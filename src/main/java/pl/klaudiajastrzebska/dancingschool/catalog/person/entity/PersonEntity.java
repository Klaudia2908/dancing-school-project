package pl.klaudiajastrzebska.dancingschool.catalog.person.entity;

import lombok.Builder;
import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.PersonDto;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;

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

    @OneToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "ID_TYP_OSOBY", referencedColumnName = "ID")
    private PersonTypeEntity personType;

    public PersonDto toDto(){
        return PersonDto
                .builder()
                .login(getUser().getLogin())
                .firstName(firstName)
                .lastName(lastName)
                .description(description)
                .gender(gender)
                .build();
    }
}
