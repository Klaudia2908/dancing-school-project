package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddScheduleCommand {
    AddScheduleFormData addScheduleFormData;
    String schoolIdentifier;
    String courseUUID;
    String principalUserName;
}
