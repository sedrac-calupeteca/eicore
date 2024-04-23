package com.eig.eicore.service

import com.eig.eicore.model.concrect.Person
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.temporal.ChronoUnit

@Service
class TokenService(
    val jwtEncoder: JwtEncoder,
) {

    fun generatorToken(person: Person): String {
        val issuedAt = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("eicore")
            .issuedAt(issuedAt)
            .expiresAt(issuedAt.plus(15, ChronoUnit.MINUTES))
            .subject(person.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }

}