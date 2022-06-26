package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.AgeGroupEntity;

import java.util.Optional;

interface AgeGroupRepository extends JpaRepository<AgeGroupEntity, Long> {
    Optional<AgeGroupEntity> findByValue(String value);
}
