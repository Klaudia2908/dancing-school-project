package pl.klaudiajastrzebska.dancingschool.catalog.person.entity;

import lombok.Data;
import org.apache.logging.log4j.util.Strings;
import pl.klaudiajastrzebska.dancingschool.catalog.person.dto.EmployeeDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SZKOLY_PRACOWNICY")
public class SchoolEmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_SZKOLY", referencedColumnName = "ID")
    private SchoolAddressEntity school;

    @ManyToOne
    @JoinColumn(name = "ID_PRACOWNIKA", referencedColumnName = "ID")
    private PersonEntity employee;

    public EmployeeDto toDto() {
        UserEntity user = employee.getUser() == null ? new UserEntity() : employee.getUser();

        return EmployeeDto
                .builder()
                .personId(employee.getId())
                .login(user.getLogin())
                .description(employee.getDescription())
                .lastName(employee.getLastName())
                .firstName(employee.getFirstName())
                .gender(employee.getGender())
                .birthDate(employee.getBirthDate())
                .schoolNameAndAddress(getSchoolInformation(school))
                .build();
    }

    private String getSchoolInformation(SchoolAddressEntity schoolAddressEntity) {
        String schoolName = schoolAddressEntity.getSchool().getName();
        String street = schoolAddressEntity.getStreet();
        String city = schoolAddressEntity.getCity();
        String numberOfTheBuilding = schoolAddressEntity.getNumberOfTheBuilding();
        String flatNumber = schoolAddressEntity.getFlatNumber();

        if (!flatNumber.isBlank()) {
            numberOfTheBuilding = numberOfTheBuilding + "/" + flatNumber;
        }

        return schoolName + " " + street + " " + numberOfTheBuilding + " " + city;
    }
}
