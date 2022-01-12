package pl.klaudiajastrzebska.dancingschool.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.entity.PersonEntity;

interface PersonRepository extends JpaRepository<PersonEntity, Long> {
}
