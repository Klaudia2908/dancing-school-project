package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonTypeEntity;

import java.util.Optional;

public interface PersonTypeRepository extends JpaRepository<PersonTypeEntity, Long> {
    Optional<PersonTypeEntity> findByValue(String value);
}
