package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity.ScheduleEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {

    @Query(value = "SELECT * FROM HARMONOGRAMY H " +
            "JOIN KURSY K ON K.ID = H.ID_KURS " +
            "JOIN ADRES_SZKOLY ADR ON ADR.ID = K.ID_ADRES_SZKOLY " +
            "WHERE ADR.IDENTYFIKATOR = :schoolIdentifier " +
            "AND K.UUID = :courseUUID", nativeQuery = true)
    List<ScheduleEntity> findAllForSchoolAndCourse(String schoolIdentifier, String courseUUID);

    Optional<ScheduleEntity> findByUuid(String uuid);
}
