package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.klaudiajastrzebska.dancingschool.security.dto.UserDto;

import java.util.List;

@RequiredArgsConstructor
class CustomAuthenticationProvider implements AuthenticationProvider {
    private final SecurityService securityService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDto userDto = securityService.getUser(login);

        if(!passwordEncoder.matches(password, userDto.getPassword())){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userDto.getLogin(), userDto.getPassword(), mapToAuthority(userDto.getRole().getName()));
    }

    private List<GrantedAuthority> mapToAuthority(String role) {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
