package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolEntity;

import java.util.List;

interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {

    @Query(value = "SELECT * FROM SZKOLY SZK " +
            "JOIN ADRES_SZKOLY ADR_SZK ON SZK.ID = ADR_SZK.ID_SZKOLY " +
            "WHERE UPPER(ADR_SZK.MIEJSCOWOSC) = UPPER(:city)", nativeQuery = true)
    List<SchoolEntity> findSchoolsByCity(String city);
}
