package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonContactEntity;

public interface PersonContactRepository extends JpaRepository<PersonContactEntity, Long> {
}
