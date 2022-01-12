package pl.klaudiajastrzebska.dancingschool.security.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationException extends RuntimeException {
    private final String message;
}
