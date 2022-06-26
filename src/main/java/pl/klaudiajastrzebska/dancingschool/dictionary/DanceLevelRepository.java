package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceLevelEntity;

import java.util.Optional;

interface DanceLevelRepository extends JpaRepository<DanceLevelEntity, Long> {
    Optional<DanceLevelEntity> findByValue(String value);
}
