package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity.EnrollmentEntity;

public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
}
