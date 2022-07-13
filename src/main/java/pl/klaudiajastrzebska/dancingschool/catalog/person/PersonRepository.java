package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    @Query(value = "select * from osoby os " +
            "join S_TYP_OSOBY typ_os on typ_os.id = os.ID_TYP_OSOBY " +
            "left join SZKOLY_PRACOWNICY szk_prac on szk_prac.id_pracownika = os.id " +
            "where typ_os.nazwa ='EMPLOYEE' " +
            "and (nvl(szk_prac.DATA_KONCA_ZATR, sysdate + 1) < sysdate OR szk_prac.id_pracownika is null)", nativeQuery = true)
    List<PersonEntity> findAllEmployeesWithoutSchool();


    @Query(value = "SELECT * FROM OSOBY OS " +
            "JOIN USERS U ON OS.ID_USER = U.ID " +
            "WHERE U.LOGIN = :userName", nativeQuery = true)
    Optional<PersonEntity> findByAttachedUserName(String userName);
}
