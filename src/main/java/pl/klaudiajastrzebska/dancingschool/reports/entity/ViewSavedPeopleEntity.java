package pl.klaudiajastrzebska.dancingschool.reports.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.reports.dto.SignedPeopleReportDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "V_ZAPISANE_OSOBY")
public class ViewSavedPeopleEntity {
    @Id
    private long id;

    @Column(name = "NAZWA_KURSU")
    private String courseName;

    @Column(name = "NAZWA_SZKOLY")
    private String schoolName;

    @Column(name = "MIEJSCOWOSC_SZKOLY")
    private String schoolCity;

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

    @Column(name = "ZAPISANA_ILOSC_OSOB")
    private String savedPeopleCount;

    @Column(name = "MAX_OSOB")
    private String maxCapacity;

    @Column(name = "STATUS_ZAPIS")
    private String registrationStatus;

    public SignedPeopleReportDto toDto() {
        return SignedPeopleReportDto
                .builder()
                .courseName(courseName)
                .schoolName(schoolName)
                .schoolCity(schoolCity)
                .schoolIdentifier(schoolIdentifier)
                .startDate(startDate)
                .endDate(endDate)
                .hourFrom(hourFrom)
                .hourTo(hourTo)
                .dayOfWeek(dayOfWeek)
                .savedPeopleCount(savedPeopleCount)
                .maxCapacity(maxCapacity)
                .registrationStatus(registrationStatus)
                .build();
    }
}