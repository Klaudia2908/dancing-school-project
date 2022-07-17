package pl.klaudiajastrzebska.dancingschool.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.klaudiajastrzebska.dancingschool.reports.entity.ViewSavedPeopleEntity;

import java.util.List;

interface ViewSavedPeopleRepository extends JpaRepository<ViewSavedPeopleEntity, Long> {
    List<ViewSavedPeopleEntity> findBySchoolIdentifierInOrderByStartDate(List<String> schoolIdentifiers);
}
