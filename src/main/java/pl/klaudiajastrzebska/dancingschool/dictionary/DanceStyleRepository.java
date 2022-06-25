package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceStyleEntity;

interface DanceStyleRepository extends JpaRepository<DanceStyleEntity, Long> {
}
