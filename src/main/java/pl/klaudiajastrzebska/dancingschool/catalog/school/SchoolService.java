package pl.klaudiajastrzebska.dancingschool.catalog.school;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.mapper.SchoolMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SchoolService {
    private final SchoolAddressRepository schoolAddressRepository;

    public List<SchoolDto> getSchoolsByCity(String city) {
        if(StringUtils.isBlank(city)){
            return schoolAddressRepository
                    .findAll()
                    .stream()
                    .map(SchoolMapper::mapToDto)
                    .collect(Collectors.toList());
        }

        return schoolAddressRepository.findSchoolsByCity(city)
                .stream()
                .map(SchoolMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public SchoolDto getSchoolByIdentifier(String schoolIdentifier) {
        return schoolAddressRepository.findSchoolByIdentifier(schoolIdentifier)
                .map(SchoolMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find school for given identifier: " + schoolIdentifier));
    }
}
