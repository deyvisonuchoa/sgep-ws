package br.com.sgep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.sgep.security.JwtConfigurer;
import br.com.sgep.security.JwtTokenProvider;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {		
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.httpBasic().disable()
			.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
				.antMatchers("/v1/auth").permitAll()
				.antMatchers("/**").authenticated()
			.and()
			.apply(new JwtConfigurer(tokenProvider));
	}
	
	
}