package pl.klaudiajastrzebska.dancingschool.catalog.school.mapper;

import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDefinitionDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolEntity;

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
                .closeDate(entity.getCloseDate())
                .build();
    }

    public static SchoolDefinitionDto mapToDto(SchoolEntity entity) {
        return SchoolDefinitionDto
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
