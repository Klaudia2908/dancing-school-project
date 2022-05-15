package pl.klaudiajastrzebska.dancingschool.catalog.school;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddAddressToExistiongSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.AddNewSchoolCommand;
import pl.klaudiajastrzebska.dancingschool.administration.school.dto.EditSchoolDataCommand;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDefinitionDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.dto.SchoolDto;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolAddressEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolEntity;
import pl.klaudiajastrzebska.dancingschool.catalog.school.mapper.SchoolMapper;
import pl.klaudiajastrzebska.dancingschool.validaton.ValidationService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class SchoolService {
    private final SchoolAddressRepository schoolAddressRepository;
    private final SchoolRepository schoolRepository;
    private final ValidationService validationService;

    public List<SchoolDto> getSchoolsByCity(String city) {
        if (StringUtils.isBlank(city)) {
            return schoolAddressRepository
                    .findAllActiveSchoolAddresses()
                    .stream()
                    .map(SchoolMapper::mapToDto)
                    .collect(Collectors.toList());
        }

        return schoolAddressRepository.findSchoolsByCity(city)
                .stream()
                .map(SchoolMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<SchoolDefinitionDto> getSchoolDefinitions() {
        return schoolRepository
                .findAll()
                .stream()
                .map(SchoolMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public SchoolDto getSchoolByIdentifier(String schoolIdentifier) {
        return schoolAddressRepository.findSchoolByIdentifier(schoolIdentifier)
                .map(SchoolMapper::mapToDto)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find school for given identifier: " + schoolIdentifier));
    }

    @Transactional
    public void deleteSchoolByShortName(String schoolShortName) {
        SchoolAddressEntity schoolByIdentifier = schoolAddressRepository.findSchoolByIdentifier(schoolShortName)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find school for given identifier: " + schoolShortName));

        schoolByIdentifier.setCloseDate(LocalDateTime.now());
    }

    public void addNewSchool(AddNewSchoolCommand command) {
        validationService.validate(command);
        SchoolEntity entity = new SchoolEntity();
        entity.setName(command.getName());
        entity.setDescription(command.getDescription());

        schoolRepository.save(entity);
    }

    public void addAddressToExistingSchoolId(Long schoolId, AddAddressToExistiongSchoolCommand command) {
        validationService.validate(command);
        SchoolEntity schoolEntity = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find school for given id: " + schoolId));

        SchoolAddressEntity schoolAddress = new SchoolAddressEntity();
        schoolAddress.setCity(command.getCity());
        schoolAddress.setCloseDate(null);
        schoolAddress.setShortName(command.getShortName());
        schoolAddress.setNumberOfTheBuilding(command.getNumberOfTheBuilding());
        schoolAddress.setStreet(command.getStreet());
        schoolAddress.setFlatNumber(command.getFlatNumber());
        schoolAddress.setPostCode(command.getPostCode());
        schoolAddress.setSchool(schoolEntity);

        schoolAddressRepository.save(schoolAddress);
    }

    @Transactional
    public void editSchoolData(EditSchoolDataCommand editSchoolDataCommand, String schoolIdentifier) {
        validationService.validate(editSchoolDataCommand);

        SchoolAddressEntity schoolAddressEntity = schoolAddressRepository.findSchoolByIdentifier(schoolIdentifier).get();
        SchoolEntity schoolEntity = schoolAddressEntity.getSchool();

        schoolEntity.setName(editSchoolDataCommand.getName());
        schoolEntity.setDescription(editSchoolDataCommand.getDescription());

        schoolRepository.save(schoolEntity);

        schoolAddressEntity.setCity(editSchoolDataCommand.getCity());
        schoolAddressEntity.setShortName(editSchoolDataCommand.getShortName());
        schoolAddressEntity.setNumberOfTheBuilding(editSchoolDataCommand.getNumberOfTheBuilding());
        schoolAddressEntity.setStreet(editSchoolDataCommand.getStreet());
        schoolAddressEntity.setFlatNumber(editSchoolDataCommand.getFlatNumber());
        schoolAddressEntity.setPostCode(editSchoolDataCommand.getPostCode());
        schoolAddressEntity.setSchool(schoolEntity);

        schoolAddressRepository.save(schoolAddressEntity);
    }

    public List<SchoolDto> getSchoolsAvailableForAttachingForLogin(String employeeLogin) {
        return schoolAddressRepository.findAllAddressesAvailableForAttachingForLogin(employeeLogin)
                .stream()
                .map(SchoolMapper::mapToDto)
                .toList();
    }
}
