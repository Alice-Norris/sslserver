package com.snhu.sslserver;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

//security configuration to enable CORS to prevent cross-site scripting, but disables csrf and allows stateless session creation
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	//configure security to allow unauthenticated users to use the /hash routing,
	//all others will require login.
	protected void configure(HttpSecurity security) throws Exception {
		security = security.cors().and().csrf().disable();
		security = security
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and();
		
		security.authorizeRequests(). 
			antMatchers(HttpMethod.GET, "/hash").permitAll();
	}
}