package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.ContactTypeEntity;

import java.util.Optional;

public interface ContactTypeRepository extends JpaRepository<ContactTypeEntity, Long> {
    Optional<ContactTypeEntity> findByValue(String value);
}
