package pl.klaudiajastrzebska.dancingschool.catalog.course.entity;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.CourseDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.AgeGroupEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceLevelEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceStyleEntity;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "KURSY")
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAZWA")
    private String name;

    @Lob
    @Column(name = "OPIS")
    private String description;

    @Column(name = "CENA")
    private BigDecimal price;

    private String uuid;

    @ManyToOne
    @JoinColumn(name = "ID_ADRES_SZKOLY", referencedColumnName = "ID")
    private SchoolAddressEntity schoolAddress;

    @ManyToOne
    @JoinColumn(name = "ID_STYL_TANECZNY", referencedColumnName = "ID")
    private DanceStyleEntity style;

    @ManyToOne
    @JoinColumn(name = "ID_POZIOM_ZAAWANSOWANIA", referencedColumnName = "ID")
    private DanceLevelEntity level;

    @ManyToOne
    @JoinColumn(name = "ID_GRUPA_WIEKOWA", referencedColumnName = "ID")
    private AgeGroupEntity ageGroupEntity;

    public CourseDto toDto() {
        String buildingNumber = StringUtils.isBlank(schoolAddress.getFlatNumber()) ? schoolAddress.getNumberOfTheBuilding() : schoolAddress.getNumberOfTheBuilding() + "/" + schoolAddress.getFlatNumber();
        String address = schoolAddress.getCity() + " " + schoolAddress.getStreet() + " " + buildingNumber;

        return CourseDto
                .builder()
                .name(name)
                .description(description)
                .price(price)
                .level(level.getValue())
                .style(style.getValue())
                .ageGroup(ageGroupEntity.getValue())
                .address(address)
                .build();
    }
}
