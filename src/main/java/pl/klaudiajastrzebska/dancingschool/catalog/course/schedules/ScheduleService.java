package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.course.CourseRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.course.entity.CourseEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.AddScheduleFormData;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto.ScheduleDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.entity.ScheduleEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.person.PersonRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.person.entity.PersonEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.DayRepository;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DayEntity;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ValidationService validationService;
    private final PersonRepository personRepository;
    private final CourseRepository courseRepository;
    private final DayRepository dayRepository;


    public List<ScheduleDto> getAllSchedulesForSchoolAndCourse(String schoolIdentifier, String courseUUID) {
        return scheduleRepository.findAllForSchoolAndCourse(schoolIdentifier, courseUUID)
                .stream()
                .map(ScheduleEntity::toDto)
                .toList();
    }

    public void addScheduleForSchoolAndPrincipal(AddScheduleCommand command) {
        AddScheduleFormData addScheduleFormData = command.getAddScheduleFormData();
        validationService.validate(addScheduleFormData);

        scheduleRepository.save(buildScheduleEntity(command));
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
        scheduleEntity.setMaxCapacity(scheduleFormData.getMaxCapacity());

        return scheduleEntity;
    }
}
