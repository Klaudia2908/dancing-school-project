package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DayEntity;

import java.util.Optional;

public interface DayRepository extends JpaRepository<DayEntity, Long> {
    Optional<DayEntity> findByValue(String value);
}
