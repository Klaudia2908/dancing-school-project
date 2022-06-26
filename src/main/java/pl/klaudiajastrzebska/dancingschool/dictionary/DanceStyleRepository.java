package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceStyleEntity;

import java.util.Optional;

interface DanceStyleRepository extends JpaRepository<DanceStyleEntity, Long> {
    Optional<DanceStyleEntity> findByValue(String value);
}
