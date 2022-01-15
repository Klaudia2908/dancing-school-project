package pl.klaudiajastrzebska.dancingschool.catalog.school.mapper;

import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolEntity;

public class SchoolMapper {
    public static SchoolDto mapToDto(SchoolEntity entity){
        return SchoolDto
                .builder()
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
