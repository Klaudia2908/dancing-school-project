package pl.klaudiajastrzebska.dancingschool.dictionary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("DictionaryConfig")
class Config {

    @Bean
    DictionaryService dictionaryService(DanceLevelRepository danceLevelRepository, DanceStyleRepository danceStyleRepository, AgeGroupRepository ageGroupRepository, DayRepository dayRepository) {
        return new DictionaryService(danceLevelRepository, danceStyleRepository, ageGroupRepository, dayRepository);
    }
}
