package pl.klaudiajastrzebska.dancingschool.catalog.school.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ADRES_SZKOLY")
public class SchoolAddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ULICA")
    private String street;

    @Column(name = "NR_BUDYNKU")
    private String numberOfTheBuilding;

    @Column(name = "NR_LOKALU")
    private String flatNumber;

    @Column(name = "MIEJSCOWOSC")
    private String city;

    @Column(name = "KOD_POCZTOWY")
    private String postCode;

    @Column(name = "IDENTYFIKATOR")
    private String shortName;

    @ManyToOne
    @JoinColumn(name = "ID_SZKOLY", referencedColumnName = "ID")
    private SchoolEntity school;
}
