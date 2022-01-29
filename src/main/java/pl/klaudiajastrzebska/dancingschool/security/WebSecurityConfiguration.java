package pl.klaudiajastrzebska.dancingschool.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@RequiredArgsConstructor
class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/administration").hasAuthority("ADMIN")
                .antMatchers("/administration/*").hasAuthority("ADMIN")

                .antMatchers("/login").permitAll()
                .antMatchers("/login-error").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/schools").permitAll()
                .antMatchers("/*").permitAll()

                .antMatchers("/assets/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()

                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login-error")
                .and()
                .logout()
                .logoutSuccessUrl("/home");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }

    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}
