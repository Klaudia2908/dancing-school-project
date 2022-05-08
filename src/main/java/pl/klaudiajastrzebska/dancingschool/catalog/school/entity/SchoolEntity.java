package pl.klaudiajastrzebska.dancingschool.catalog.school.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SZKOLY")
public class SchoolEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "NAZWA")
    private String name;

    @Lob
    @Column(name = "OPIS")
    private String description;
}
