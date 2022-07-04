package pl.klaudiajastrzebska.dancingschool.catalog.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.course.entity.CourseEntity;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Long> {

    @Query(value = "SELECT * FROM KURSY K "+
            "JOIN ADRES_SZKOLY SA ON K.ID_ADRES_SZKOLY = SA.ID " +
            "WHERE SA.IDENTYFIKATOR= :schoolIdentifier ",nativeQuery = true)
    List<CourseEntity> findAllBySchoolIdentifier(String schoolIdentifier);

    Optional<CourseEntity> findByUuid(String courseUUID);
}
