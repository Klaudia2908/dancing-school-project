package pl.klaudiajastrzebska.dancingschool.dictionary.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceLevel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "S_POZIOM_ZAAWANSOWANIA")
public class DanceLevelEntity {
    @Id
    private Long id;

    @Column(name = "NAZWA")
    private String value;

    public DanceLevel toDto(){
        return DanceLevel.of(value);
    }
}
