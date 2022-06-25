package pl.klaudiajastrzebska.dancingschool.dictionary;


import lombok.RequiredArgsConstructor;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.AgeGroup;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceLevel;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceStyle;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.AgeGroupEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceLevelEntity;
import pl.klaudiajastrzebska.dancingschool.dictionary.entity.DanceStyleEntity;

import java.util.List;

@RequiredArgsConstructor
public class DictionaryService {
    private final DanceLevelRepository danceLevelRepository;
    private final DanceStyleRepository danceStyleRepository;
    private final AgeGroupRepository ageGroupRepository;

    public List<String> getAgeGroups(){
        return ageGroupRepository.findAll()
                .stream()
                .map(AgeGroupEntity::toDto)
                .map(AgeGroup::getValue)
                .toList();
    }

    public List<String> getDanceStyles(){
        return danceStyleRepository.findAll()
                .stream()
                .map(DanceStyleEntity::toDto)
                .map(DanceStyle::getValue)
                .toList();
    }

    public List<String> getDanceLevels(){
        return danceLevelRepository.findAll()
                .stream()
                .map(DanceLevelEntity::toDto)
                .map(DanceLevel::getValue)
                .toList();
    }
}
