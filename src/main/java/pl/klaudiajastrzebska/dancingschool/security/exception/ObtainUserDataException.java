package pl.klaudiajastrzebska.dancingschool.security.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ObtainUserDataException extends RuntimeException {
    private final String message;
}
