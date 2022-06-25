package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.SessionScope;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserDto;
import pl.klaudiajastrzebska.dancingschool.security.exception.ObtainUserDataException;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
class CustomAuthenticationProvider implements AuthenticationProvider {
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        try{
            UserDto userDto = securityService.getUser(login);
            if(!passwordEncoder.matches(password, userDto.getPassword())){
                return null;
            }

            if(Objects.nonNull(userDto.getRemovalDate())){
                return null;
            }

            return new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword(), mapToAuthority(userDto.getRole().getName()));

        } catch (ObtainUserDataException e){
            log.error("Niepoprawne dane logowania dla user: " + login);
            return null;
        }
    }

    private List<GrantedAuthority> mapToAuthority(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
