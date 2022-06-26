package pl.klaudiajastrzebska.dancingschool.catalog.course;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddCourseFormDataDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddNewCourseCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.CourseDto;
import pl.klaudiajastrzebska.dancingschool.catalog.course.entity.CourseEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.SchoolAddressRepository;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.DictionaryService;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CourseService {
    private final SchoolAddressRepository schoolAddressRepository;
    private final CourseRepository courseRepository;

    private final ValidationService validationService;
    private final DictionaryService dictionaryService;

    public List<CourseDto> getAllCoursesForSchoolIdentifier(String schoolIdentifier) {
        return courseRepository.findAllBySchoolIdentifier(schoolIdentifier)
                .stream()
                .map(CourseEntity::toDto)
                .toList();
    }

    public void addNewCourse(AddNewCourseCommand command) {
        validationService.validate(command.getFormDataDto());
        CourseEntity courseEntity = buildCourseEntity(command);

        courseRepository.save(courseEntity);
    }

    private CourseEntity buildCourseEntity(AddNewCourseCommand command) {
        AddCourseFormDataDto formDataDto = command.getFormDataDto();
        SchoolAddressEntity schoolAddressEntity = schoolAddressRepository.findSchoolByIdentifier(command.getSchoolIdentifier()).get();

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(formDataDto.getName());
        courseEntity.setDescription(formDataDto.getDescription());
        courseEntity.setPrice(formDataDto.getPrice());
        courseEntity.setUuid(UUID.randomUUID().toString());
        courseEntity.setSchoolAddress(schoolAddressEntity);
        courseEntity.setStyle(dictionaryService.getDanceStyleEntity(formDataDto.getStyle()));
        courseEntity.setLevel(dictionaryService.getDanceLevelEntity(formDataDto.getLevel()));
        courseEntity.setAgeGroupEntity(dictionaryService.getAgeGroupEntity(formDataDto.getAgeGroup()));

        return courseEntity;
    }
}
