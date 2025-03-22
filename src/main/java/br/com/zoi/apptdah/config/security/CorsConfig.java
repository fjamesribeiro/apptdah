package br.com.zoi.apptdah.config.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();

		// Permitir todas as origens comuns para desenvolvimento
		config.setAllowedOrigins(Arrays.asList(
				"http://localhost:8081",
				"http://192.168.3.2:8081",
				"http://localhost:19006",
				"http://localhost:19000",
				"http://localhost:19001",
				"http://localhost:19002",
				"http://192.168.0.100:8081",
				"exp://localhost:8081",
				"exp://192.168.3.2:8081"));

		// Permitir todos os métodos necessários
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

		// Permitir todos os cabeçalhos necessários
		config.setAllowedHeaders(Arrays.asList(
				"Authorization",
				"Content-Type",
				"Accept",
				"Origin",
				"X-Requested-With",
				"Access-Control-Request-Method",
				"Access-Control-Request-Headers"));

		// Expor cabeçalhos necessários
		config.setExposedHeaders(Arrays.asList(
				"Access-Control-Allow-Origin",
				"Access-Control-Allow-Credentials"));

		// Permitir credenciais (importante para Axios withCredentials)
		config.setAllowCredentials(true);

		// Tempo de cache para preflight
		config.setMaxAge(3600L);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
