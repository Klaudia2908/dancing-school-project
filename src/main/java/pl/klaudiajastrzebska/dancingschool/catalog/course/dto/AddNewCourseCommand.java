package pl.klaudiajastrzebska.dancingschool.catalog.course.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class AddNewCourseCommand {
    AddCourseFormDataDto formDataDto;

    String schoolIdentifier;
}
