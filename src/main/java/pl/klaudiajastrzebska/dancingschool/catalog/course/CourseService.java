package pl.klaudiajastrzebska.dancingschool.catalog.course;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.course.dto.AddNewCourseCommand;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

@RequiredArgsConstructor
public class CourseService {
    private final ValidationService validationService;

    public void addNewCourse(AddNewCourseCommand command){
        validationService.validate(command.getFormDataDto());
    }
}
