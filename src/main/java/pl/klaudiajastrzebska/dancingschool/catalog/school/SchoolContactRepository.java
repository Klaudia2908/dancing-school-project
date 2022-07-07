package pl.klaudiajastrzebska.dancingschool.catalog.school;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolContactEntity;

public interface SchoolContactRepository extends JpaRepository<SchoolContactEntity, Long> {

}
