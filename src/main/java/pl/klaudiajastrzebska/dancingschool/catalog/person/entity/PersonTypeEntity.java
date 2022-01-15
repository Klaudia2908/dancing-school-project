package pl.klaudiajastrzebska.dancingschool.catalog.person.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_TYP_OSOBY")
public class PersonTypeEntity {
    @Id
    private long id;

    @Column(name = "NAZWA")
    private String value;
}
