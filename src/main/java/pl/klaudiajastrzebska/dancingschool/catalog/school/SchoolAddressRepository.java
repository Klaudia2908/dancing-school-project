package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;

import java.util.List;

public interface SchoolAddressRepository extends JpaRepository<SchoolAddressEntity, Long> {
    @Query(value = "SELECT * FROM ADRES_SZKOLY ADR_SZK " +
            "JOIN SZKOLY SZK ON SZK.ID = ADR_SZK.ID_SZKOLY " +
            "WHERE UPPER(ADR_SZK.MIEJSCOWOSC) = UPPER(:city)", nativeQuery = true)
    List<SchoolAddressEntity> findSchoolsByCity(String city);

}
