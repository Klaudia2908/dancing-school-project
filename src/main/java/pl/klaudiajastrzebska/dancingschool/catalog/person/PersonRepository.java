package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;

interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
