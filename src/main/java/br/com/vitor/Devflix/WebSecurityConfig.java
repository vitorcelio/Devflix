package br.com.vitor.Devflix;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin(login -> 
						login.loginPage("/login")
						.defaultSuccessUrl("/admin/cursos", true)
						.permitAll()
			).logout(logout -> logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/home")
			).csrf().disable();
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		@SuppressWarnings("deprecation")
		UserDetails user = User
								.withDefaultPasswordEncoder()
								.username("vitorcelio") 
								.password("12345")
								.roles("ADMIN")
								.build();
		
		return new InMemoryUserDetailsManager(user);
	}
	
	
}
