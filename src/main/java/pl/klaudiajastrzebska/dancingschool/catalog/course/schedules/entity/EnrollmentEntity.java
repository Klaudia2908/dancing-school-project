package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ZAPISY")
public class EnrollmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ID_OSOBY", referencedColumnName = "ID")
    private PersonEntity person;

    @Column(name = "DATA_ZAPISU")
    private LocalDateTime enrollmentDate;

    @ManyToOne
    @JoinColumn(name = "ID_HARMONOGRAMU", referencedColumnName = "ID")
    private ScheduleEntity schedule;

    @Column(name = "KIEDY_ZAPLACONO")
    private LocalDateTime paymentDate;
}
