package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceLevelEntity;

interface DanceLevelRepository extends JpaRepository<DanceLevelEntity, Long> {
}
