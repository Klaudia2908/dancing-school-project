package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;

import java.util.List;
import java.util.Optional;

public interface SchoolAddressRepository extends JpaRepository<SchoolAddressEntity, Long> {
    @Query(value = "SELECT * FROM ADRES_SZKOLY ADR_SZK " +
            "JOIN SZKOLY SZK ON SZK.ID = ADR_SZK.ID_SZKOLY " +
            "WHERE UPPER(ADR_SZK.MIEJSCOWOSC) = UPPER(:city) " +
            "AND NVL(ADR_SZK.DATA_ZAMKNIECIA, SYSDATE + 1) >= SYSDATE", nativeQuery = true)
    List<SchoolAddressEntity> findSchoolsByCity(String city);

    @Query(value = "SELECT * FROM ADRES_SZKOLY ADR_SZK " +
            "JOIN SZKOLY SZK ON SZK.ID = ADR_SZK.ID_SZKOLY " +
            "AND NVL(ADR_SZK.DATA_ZAMKNIECIA, SYSDATE + 1) >= SYSDATE", nativeQuery = true)
    List<SchoolAddressEntity> findAllActiveSchoolAddresses();

    @Query(value = "SELECT * FROM ADRES_SZKOLY ADR_SZK " +
            "JOIN SZKOLY SZK ON SZK.ID = ADR_SZK.ID_SZKOLY " +
            "WHERE UPPER(ADR_SZK.IDENTYFIKATOR) = UPPER(:identifier)", nativeQuery = true)
    Optional<SchoolAddressEntity> findSchoolByIdentifier(String identifier);

    @Query(value = "select * " +
            "from adres_szkoly adrSzk " +
            "         left join szkoly_pracownicy szkPr on adrSzk.id = szkPr.id_szkoly " +
            "         left join osoby os on os.id = szkPr.id_pracownika " +
            "         left join users us on us.id = os.id_user " +
            "where us.id is null " +
            "   or us.id not in (select us1.id " +
            "                    from szkoly_pracownicy szkPr1 " +
            "                             join osoby os1 on os1.id = szkPr1.id_pracownika " +
            "                             join users us1 on us1.id = os1.id_user " +
            "                                where us1.login = :login )", nativeQuery = true)
    List<SchoolAddressEntity> findAllAddressesAvailableForAttachingForLogin(String login);
}