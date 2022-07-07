package pl.klaudiajastrzebska.dancingschool.catalog.school.entity;


import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.ContactTypeEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "KONTAKT_SZKOLY")
public class SchoolContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_S_TYP_KONTAKTU", referencedColumnName = "ID")
    ContactTypeEntity contactType;

    @ManyToOne
    @JoinColumn(name = "ID_ADRES_SZKOLY", referencedColumnName = "ID")
    SchoolAddressEntity schoolAddress;

    @Column(name = "WARTOSC")
    String value;
}
