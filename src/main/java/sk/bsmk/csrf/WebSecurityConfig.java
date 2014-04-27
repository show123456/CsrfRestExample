package sk.bsmk.csrf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by bsmk on 4/27/14.
 */
@ComponentScan
@EnableAutoConfiguration
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private MapCsrfTokenRepository tokenRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic().and()
        // use simple MapTokenRepository instead of default HttpSessionCsrfTokenRepository
        .csrf().csrfTokenRepository(tokenRepository).and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/info").permitAll()
        .anyRequest().hasRole("USER");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
        .inMemoryAuthentication()
        .withUser("user").password("password").roles("USER");
  }

}