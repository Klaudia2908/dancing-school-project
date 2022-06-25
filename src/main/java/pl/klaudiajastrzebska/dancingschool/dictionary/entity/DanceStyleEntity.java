package pl.klaudiajastrzebska.dancingschool.dictionary.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceLevel;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.DanceStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "S_STYL_TANECZNY")
public class DanceStyleEntity {
    @Id
    private Long id;

    @Column(name = "NAZWA")
    private String value;

    public DanceStyle toDto(){
        return DanceStyle.of(value);
    }
}
