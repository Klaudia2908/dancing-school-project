package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.SchoolEmployeeEntity;

import java.util.List;
import java.util.Optional;


public interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployeeEntity, Long> {

    @Query(value = "select * from szkoly_pracownicy szkpr " +
            "join osoby os on szkpr.id_pracownika = os.id " +
            "join s_typ_osoby typ on typ.id = os.id_typ_osoby " +
            "where typ.nazwa = 'EMPLOYEE'" +
            "and NVL(szkpr.DATA_KONCA_ZATR, SYSDATE + 1) > SYSDATE", nativeQuery = true)
    List<SchoolEmployeeEntity> findAllEmployees();

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
            "where login = :employeeUserName " +
            "and NVL(sp.DATA_KONCA_ZATR, SYSDATE + 1) > SYSDATE", nativeQuery = true)
    List<SchoolEmployeeEntity> findAllSchoolsForGivenEmployee(String employeeUserName);

    @Query(value = "select * from szkoly_pracownicy sp " +
            "join osoby o on o.id = sp.id_pracownika " +
            "join adres_szkoly adr on adr.id = sp.id_szkoly " +
            "join s_typ_osoby typ_os on typ_os.id = o.id_typ_osoby " +
            "where typ_os.nazwa='INSTRUCTOR' " +
            "and adr.identyfikator=:schoolIdentifier " +
            "and NVL(sp.DATA_KONCA_ZATR, SYSDATE + 1) > SYSDATE", nativeQuery = true)
    List<SchoolEmployeeEntity> findAllInstructorsForSchoolIdentifier(String schoolIdentifier);

    @Query(value = "select * from szkoly_pracownicy sp " +
            "join osoby o on o.id = sp.id_pracownika " +
            "join adres_szkoly adr on adr.id = sp.id_szkoly " +
            "join s_typ_osoby typ_os on typ_os.id = o.id_typ_osoby " +
            "where typ_os.nazwa='EMPLOYEE' " +
            "and adr.identyfikator=:schoolIdentifier " +
            "and NVL(sp.DATA_KONCA_ZATR, SYSDATE + 1) > SYSDATE", nativeQuery = true)
    List<SchoolEmployeeEntity> findAllEmployeesForSchoolIdentifier(String schoolIdentifier);


    @Query(value = "select * from szkoly_pracownicy sp " +
            "join osoby o on o.id = sp.id_pracownika " +
            "join users u on u.id = o.id_user " +
            "join adres_szkoly adrSzk on adrSzk.id = sp.id_szkoly " +
            "where login = :login " +
            "and adrSzk.identyfikator = :schoolIdentifier " +
            "order by id desc limit 1", nativeQuery = true)
    Optional<SchoolEmployeeEntity> findEmployeeWithLoginAndSchoolIdentifier(String login, String schoolIdentifier);
}
