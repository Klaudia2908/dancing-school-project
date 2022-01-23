package pl.klaudiajastrzebska.dancingschool.catalog.school.mapper;

import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;

public class SchoolMapper {
    public static SchoolDto mapToDto(SchoolAddressEntity entity) {
        return SchoolDto
                .builder()
                .name(entity.getSchool().getName())
                .description(entity.getSchool().getDescription())
                .city(entity.getCity())
                .flatNumber(entity.getFlatNumber())
                .numberOfTheBuilding(entity.getNumberOfTheBuilding())
                .postCode(entity.getPostCode())
                .street(entity.getStreet())
                .shortName(entity.getShortName())
                .build();
    }
}
