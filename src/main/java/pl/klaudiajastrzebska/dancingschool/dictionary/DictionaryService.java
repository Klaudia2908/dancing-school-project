package pl.klaudiajastrzebska.dancingschool.dictionary;


import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.catalog.school.entity.SchoolContactEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.AgeGroup;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceLevel;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceStyle;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.Day;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.*;

import java.util.List;

@RequiredArgsConstructor
public class DictionaryService {
    private final DanceLevelRepository danceLevelRepository;
    private final DanceStyleRepository danceStyleRepository;
    private final AgeGroupRepository ageGroupRepository;
    private final DayRepository dayRepository;
    private final ContactTypeRepository contactTypeRepository;

    public List<String> getAgeGroups() {
        return ageGroupRepository.findAll()
                .stream()
                .map(AgeGroupEntity::toDto)
                .map(AgeGroup::getValue)
                .toList();
    }

    public List<String> getDanceStyles() {
        return danceStyleRepository.findAll()
                .stream()
                .map(DanceStyleEntity::toDto)
                .map(DanceStyle::getValue)
                .toList();
    }

    public List<String> getDanceLevels() {
        return danceLevelRepository.findAll()
                .stream()
                .map(DanceLevelEntity::toDto)
                .map(DanceLevel::getValue)
                .toList();
    }

    public List<String> getDays() {
        return dayRepository.findAll()
                .stream()
                .map(DayEntity::toDto)
                .map(Day::getValue)
                .toList();
    }

    public DanceLevelEntity getDanceLevelEntity(String name) {
        return danceLevelRepository.findByValue(name).get();
    }

    public DanceStyleEntity getDanceStyleEntity(String name) {
        return danceStyleRepository.findByValue(name).get();
    }

    public AgeGroupEntity getAgeGroupEntity(String name) {
        return ageGroupRepository.findByValue(name).get();
    }

    public ContactTypeEntity getContactTypeEntity(String name){
        return contactTypeRepository.findByValue(name).get();
    }
}
