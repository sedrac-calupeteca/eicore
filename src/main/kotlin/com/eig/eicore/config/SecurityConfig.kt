package com.eig.eicore.config

import com.eig.eicore.component.RSAProperties
import com.eig.eicore.service.UserDetailsServiceImpl
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    val userDetailsServiceImpl: UserDetailsServiceImpl,
    val rsaProperties: RSAProperties
) {

    @Bean
    fun daoAuthenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsServiceImpl)
        provider.setPasswordEncoder(passwordEncoder())
        return provider
    }

    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain = http
        .httpBasic { }
        .authorizeHttpRequests {
            it.requestMatchers( "/h2-console/**","/swagger-ui/**","/api/v1/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        }
        .oauth2ResourceServer { it.jwt { } }
        .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
        .passwordManagement { passwordEncoder() }
        .cors { corsConfigurationSource() }
        .csrf { it.disable() }
        .build()

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        corsConfiguration.addAllowedMethod("POST")
        corsConfiguration.addAllowedMethod("PUT")
        corsConfiguration.addAllowedMethod("DELETE")
        source.registerCorsConfiguration("/**", corsConfiguration)
        return source
    }

    @Bean
    fun jwtDecoder() : JwtDecoder = NimbusJwtDecoder.withPublicKey(rsaProperties.publicKey).build()

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwt = RSAKey.Builder(rsaProperties.publicKey).privateKey(rsaProperties.privateKey).build()
        val jwk = ImmutableJWKSet<SecurityContext>(JWKSet(jwt))
        return  NimbusJwtEncoder(jwk)
    }


}