package pl.klaudiajastrzebska.dancingschool.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.klaudiajastrzebska.dancingschool.reports.entity.ViewInstructorCoursesEntity;

import java.util.List;

interface InstructorCoursesReportRepository extends JpaRepository<ViewInstructorCoursesEntity, Long> {

    @Query(value = "select * from V_KURSY_INSTRUKTOROW where DATA_KONIEC > getdate()" +
            " AND IDENTYFIKATOR_SZKOLY IN :schoolIdentifiers " +
            "order by NAZWISKO_INSTRUKTORA, IMIE_INSTRUKTORA", nativeQuery = true)
    List<ViewInstructorCoursesEntity> findAllBySchoolIdentifiers(List<String> schoolIdentifiers);
}
