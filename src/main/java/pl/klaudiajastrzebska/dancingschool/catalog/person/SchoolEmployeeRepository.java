package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.SchoolEmployeeEntity;

import java.util.List;
import java.util.Optional;


interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployeeEntity, Long> {

    @Query(value = "select * from szkoly_pracownicy szkpr " +
            "join osoby os on szkpr.id_pracownika = os.id " +
            "join adres_szkoly adrszk on adrszk.id = szkpr.id_szkoly " +
            "join users us on us.id = os.id_user " +
            "where us.login = :userName " +
            "and adrszk.identyfikator=:schoolIdentifier", nativeQuery = true)
    Optional<SchoolEmployeeEntity> findBySchoolIdentifierAndUserName(String schoolIdentifier, String userName);

    @Query(value = "select * from szkoly_pracownicy sp " +
            "join osoby o on o.id = sp.id_pracownika " +
            "join users u on u.id = o.id_user " +
            "where login = :employeeUserName", nativeQuery = true)
    List<SchoolEmployeeEntity> findAllSchoolsForGivenEmployee(String employeeUserName);
}
