package pe.cine.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import pe.cine.jwt.JwtEntryPoint;
import pe.cine.jwt.JwtTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
				.authorizeRequests()
				.antMatchers("/auth/login","/auth/refresh","/auth/nuevousuario","/pelicula/validarimg", "/auth/buscarusuario","/auth/loginusuario").permitAll()
				//GET
				.antMatchers(HttpMethod.GET,"/distrito/**","/dia/**","/clasificacion/**","/director/**","/genero/**","/pelicula/**"
						,"/peliculagenero/**","/tiposala/**","/sede/**","/sedetiposala/**","/cine/**","/cinesede/**"
						,"/cinepelicula/**","/horario/**","/tipopublico/**","/precioboleteria/**","/valoracion/**","/descuento/**").permitAll()
				.antMatchers(HttpMethod.GET,"/auth/obtenerNombre/**").hasAnyAuthority("ROLE_SUPADMIN","ROLE_ADMIN")
				.antMatchers(HttpMethod.GET, "/auth/**").hasAnyAuthority("ROLE_SUPADMIN")
				//POST
				.antMatchers(HttpMethod.POST, "/distrito/**","/dia/**","/clasificacion/**","/director/**","/genero/**","/pelicula/**"
						,"/peliculagenero/**","/tiposala/**","/sede/**","/sedetiposala/**","/cine/**","/cinesede/**"
						,"/cinepelicula/**","/horario/**","/tipopublico/**","/precioboleteria/**").hasAnyAuthority("ROLE_SUPADMIN","ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "/valoracion/**", "/descuento/**").hasAnyAuthority("ROLE_SUPADMIN","ROLE_ADMIN","ROLE_USER")
				.antMatchers(HttpMethod.POST, "/auth/nuevo").hasAnyAuthority("ROLE_SUPADMIN")
				//PUT
				.antMatchers(HttpMethod.PUT, "/distrito/**","/dia/**","/clasificacion/**","/director/**","/genero/**","/pelicula/**"
						,"/peliculagenero/**","/tiposala/**","/sede/**","/sedetiposala/**","/cine/**","/cinesede/**"
						,"/cinepelicula/**","/horario/**","/tipopublico/**","/precioboleteria/**").hasAnyAuthority("ROLE_SUPADMIN","ROLE_ADMIN")
				.antMatchers(HttpMethod.PUT, "/valoracion/**", "/descuento/**").hasAnyAuthority("ROLE_SUPADMIN","ROLE_ADMIN","ROLE_USER")
				.antMatchers(HttpMethod.PUT, "/auth/**").hasAnyAuthority("ROLE_SUPADMIN")
				//DELETE
				.antMatchers(HttpMethod.DELETE,"/distrito/**","/dia/**","/clasificacion/**","/director/**","/genero/**","/pelicula/**"
						,"/peliculagenero/**","/tiposala/**","/sede/**","/sedetiposala/**","/cine/**","/cinesede/**"
						,"/cinepelicula/**","/horario/**","/tipopublico/**","/precioboleteria/**","/valoracion/**","/auth/**","/descuento/**").hasAnyAuthority("ROLE_SUPADMIN")
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
				.and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	

}
