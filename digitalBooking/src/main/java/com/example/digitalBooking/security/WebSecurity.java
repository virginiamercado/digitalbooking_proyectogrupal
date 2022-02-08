package com.example.digitalBooking.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;

	public WebSecurity(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and().csrf().disable()
			//.authorizeRequests().antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
				.authorizeRequests()
				.antMatchers("/admin/admin.html").hasAuthority("ADMIN")
				.antMatchers(
						"/authenticate",
						"/",
						"/modulosJs/**",
						"/productos/registro",
						"/productos/fechas/**",
						"/productos/fechas/ciudad/disponibles",
						"/ciudades",
						"/categorias",
						"/roles/**",
						"/Favoritos/**",
						"/MisReservas/**",
						"/categorias/registro",
						"/caracteristicas",
						"/caracteristicas/registro",
						"/ciudades/registro",
						"/ciudades/**",
						"/productos/**",
						"/index.html/**",
						"/Index.html/**",
						"/Home/**",
						"/header-footer/**",
						"/img/**",
						"/LogIn/**",
						"/Registro/**",
						"/DetalleProducto/**",
						"/productos/categoria/**",
						"/usuarios/registro",
						"/usuarios/**",
						"/login",
						"/listarProductos(Admin)/**",
						"/modificarProducto/**",
						"/Reserva/**",
						"/css/**",
						"/reservas/usuario/**",
						"/reservas/producto/**",
						"/CreacionProducto/**",
						"/Imagenes/**",
						"/swagger-ui.html",
						"/favoritos/**",
						"/v2/api-docs",
						"/configuration/ui",
						"/swagger-resources/**",
						"/configuration/security",
						"/swagger-ui.html",
						"/webjars/**",
						"https://bucketgrupo2digitalbooking.s3.amazonaws.com/Imagenes/**").permitAll()
				.anyRequest().authenticated().and()
               // .formLogin().loginPage("/LogIn/LogIn.html").permitAll()
                //.and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()));
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Se define la clase que recupera los usuarios y el algoritmo para procesar las passwords
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
