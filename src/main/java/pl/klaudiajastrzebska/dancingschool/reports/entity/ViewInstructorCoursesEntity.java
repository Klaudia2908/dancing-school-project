package pl.klaudiajastrzebska.dancingschool.reports.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.reports.dto.InstructorCoursesReportDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "V_KURSY_INSTRUKTOROW")
public class ViewInstructorCoursesEntity {
    @Id
    private long id;

    @Column(name = "IMIE_INSTRUKTORA")
    private String instructorFirstName;

    @Column(name = "NAZWISKO_INSTRUKTORA")
    private String instructorLastName;

    @Column(name = "NAZWA_KURSU")
    private String courseName;

    @Column(name = "NAZWA_SZKOLY")
    private String schoolName;

    @Column(name = "MIEJSCOWOSC_SZKOLY")
    private String city;

    @Column(name = "IDENTYFIKATOR_SZKOLY")
    private String schoolIdentifier;

    @Column(name = "DATA_START")
    private LocalDate startDate;

    @Column(name = "DATA_KONIEC")
    private LocalDate endDate;

    @Column(name = "GODZINA_OD")
    private String hourFrom;

    @Column(name = "GODZINA_DO")
    private String hourTo;

    @Column(name = "DZIEN_TYGODNIA")
    private String dayOfWeek;

    public InstructorCoursesReportDto toDto() {
        return InstructorCoursesReportDto
                .builder()
                .instructorFirstName(instructorFirstName)
                .instructorLastName(instructorLastName)
                .courseName(courseName)
                .schoolName(schoolName)
                .city(city)
                .schoolIdentifier(schoolIdentifier)
                .startDate(startDate)
                .endDate(endDate)
                .hourFrom(hourFrom)
                .hourTo(hourTo)
                .dayOfWeek(dayOfWeek)
                .build();
    }
}