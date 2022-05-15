package pl.klaudiajastrzebska.dancingschool.catalog.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.SchoolEmployeeEntity;

import java.util.List;

interface SchoolEmployeeRepository extends JpaRepository<SchoolEmployeeEntity, Long> {
}
