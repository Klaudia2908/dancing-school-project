package pl.klaudiajastrzebska.dancingschool.dictionary.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class DanceStyle {
    String value;
}
