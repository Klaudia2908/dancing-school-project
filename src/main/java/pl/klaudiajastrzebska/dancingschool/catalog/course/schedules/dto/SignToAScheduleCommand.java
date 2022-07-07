package pl.klaudiajastrzebska.dancingschool.catalog.course.schedules.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SignToAScheduleCommand {
    String principalName;
    String schoolIdentifier;
    String scheduleUUID;
}
