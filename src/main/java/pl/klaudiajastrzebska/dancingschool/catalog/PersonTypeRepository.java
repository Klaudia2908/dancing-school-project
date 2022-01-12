package pl.klaudiajastrzebska.dancingschool.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.entity.PersonTypeEntity;

import java.util.Optional;

interface PersonTypeRepository extends JpaRepository<PersonTypeEntity, Long> {
    Optional<PersonTypeEntity> findByValue(String value);
}
