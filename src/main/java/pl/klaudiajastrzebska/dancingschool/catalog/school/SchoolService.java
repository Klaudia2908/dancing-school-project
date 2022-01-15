package pl.klaudiajastrzebska.dancingschool.catalog.school;

import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.mapper.SchoolMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<SchoolDto> getSchoolsByCity(String city) {
        return schoolRepository.findSchoolsByCity(city)
                .stream()
                .map(SchoolMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
