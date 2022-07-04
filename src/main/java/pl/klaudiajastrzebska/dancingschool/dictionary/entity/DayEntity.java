package pl.klaudiajastrzebska.dancingschool.dictionary.entity;

import lombok.Data;
import pl.klaudiajastrzebska.dancingschool.dictionary.dto.Day;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "S_DNI_TYGODNIA")
public class DayEntity {

    @Id
    private long id;

    @Column(name = "NAZWA")
    private String value;

    public Day toDto() {
        return Day.of(value);
    }
}
