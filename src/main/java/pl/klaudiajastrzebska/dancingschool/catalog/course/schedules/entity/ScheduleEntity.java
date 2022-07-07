package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.catalog.course.entity.CourseEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.ScheduleDto;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DayEntity;
import pl.klaudiajastrzebska.dancingschool.security.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "HARMONOGRAMY")
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_S_DNI_TYGODNIA", referencedColumnName = "ID")
    private DayEntity day;

    @Column(name = "GODZINA_OD")
    private String hourFrom;

    @Column(name = "GODZINA_DO")
    private String hourTo;

    private String uuid;

    @ManyToOne
    @JoinColumn(name = "ID_KURS", referencedColumnName = "ID")
    private CourseEntity course;

    @ManyToOne
    @JoinColumn(name = "ID_OSOBY", referencedColumnName = "ID")
    private PersonEntity instructor;

    @Column(name = "MAX_OSOB")
    private long maxCapacity;

    @Column(name = "DATA_START")
    private LocalDate dateFrom;

    @Column(name = "DATA_KONIEC")
    private LocalDate dateTo;

    @Column(name = "STATUS_ZAPIS")
    private boolean canSign;

    @OneToMany(mappedBy = "schedule")
    private List<EnrollmentEntity> enrollments = List.of();

    public ScheduleDto toDto(String principalName) {
        return ScheduleDto
                .builder()
                .uuid(uuid)
                .startDate(dateFrom)
                .endDate(dateTo)
                .instructorName(instructor.getFirstName() + " " + instructor.getLastName())
                .dayOfWeek(day.getValue())
                .maxCapacity(maxCapacity)
                .hourFrom(hourFrom)
                .hourTo(hourTo)
                .isCurrentUserSignedToThatSchedule(isGivenUserSignedToThatSchedule(principalName))
                .takenPlaces(enrollments.size())
                .placesAvailable(enrollments.size() != maxCapacity)
                .build();
    }

    private boolean isGivenUserSignedToThatSchedule(String principalName) {
        return enrollments
                .stream()
                .map(EnrollmentEntity::getPerson)
                .map(PersonEntity::getUser)
                .map(UserEntity::getLogin)
                .anyMatch(principalName::equalsIgnoreCase);
    }
}
