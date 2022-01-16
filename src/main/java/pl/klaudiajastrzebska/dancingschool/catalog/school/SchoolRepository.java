package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolEntity;

interface SchoolRepository extends JpaRepository<SchoolEntity, Long> {
}
