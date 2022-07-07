package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.course.CourseRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.course.entity.CourseEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleFormData;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.ScheduleDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.SignToAScheduleCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity.EnrollmentEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity.ScheduleEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.DayRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DayEntity;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ValidationService validationService;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final DayRepository dayRepository;
    private final EnrollmentRepository enrollmentRepository;


    public List<ScheduleDto> getAllSchedulesForSchoolAndCourseIncludingPrincipal(String schoolIdentifier, String courseUUID, String principalName) {
        return scheduleRepository.findAllForSchoolAndCourse(schoolIdentifier, courseUUID)
                .stream()
                .map(entity -> entity.toDto(principalName))
                .toList();
    }

    public void addScheduleForSchoolAndPrincipal(AddScheduleCommand command) {
        AddScheduleFormData addScheduleFormData = command.getAddScheduleFormData();
        validationService.validate(addScheduleFormData);

        scheduleRepository.save(buildScheduleEntity(command));
    }

    public void signToASchedule(SignToAScheduleCommand signCommand) {
        EnrollmentEntity enrollment = buildEnrollmentEntity(signCommand);

        enrollmentRepository.save(enrollment);
    }

    private ScheduleEntity buildScheduleEntity(AddScheduleCommand command) {
        AddScheduleFormData scheduleFormData = command.getAddScheduleFormData();
        CourseEntity course = courseRepository.findByUuid(command.getCourseUUID()).get();
        PersonEntity instructor = personRepository.findById(scheduleFormData.getInstructorId()).get();
        DayEntity day = dayRepository.findByValue(scheduleFormData.getDayOfWeek()).get();

        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setCanSign(true);
        scheduleEntity.setDateFrom(scheduleFormData.getDateFrom());
        scheduleEntity.setDateTo(scheduleFormData.getDateTo());
        scheduleEntity.setHourFrom(scheduleFormData.getTimeStart());
        scheduleEntity.setHourTo(scheduleFormData.getTimeEnd());
        scheduleEntity.setCourse(course);
        scheduleEntity.setInstructor(instructor);
        scheduleEntity.setDay(day);
        scheduleEntity.setUuid(UUID.randomUUID().toString());
        scheduleEntity.setMaxCapacity(scheduleFormData.getMaxCapacity());

        return scheduleEntity;
    }

    private EnrollmentEntity buildEnrollmentEntity(SignToAScheduleCommand command) {
        LocalDateTime signDate = LocalDateTime.now();

        ScheduleEntity schedule = scheduleRepository.findByUuid(command.getScheduleUUID()).get();
        PersonEntity person = personRepository.findByAttachedUserName(command.getPrincipalName()).get();

        EnrollmentEntity enrollment = new EnrollmentEntity();
        enrollment.setSchedule(schedule);
        enrollment.setPerson(person);
        enrollment.setEnrollmentDate(signDate);
        enrollment.setPaymentDate(signDate);

        return enrollment;
    }
}
