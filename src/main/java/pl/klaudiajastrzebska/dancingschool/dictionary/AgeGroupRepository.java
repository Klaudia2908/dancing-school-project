package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.AgeGroupEntity;

interface AgeGroupRepository extends JpaRepository<AgeGroupEntity, Long> {
}
