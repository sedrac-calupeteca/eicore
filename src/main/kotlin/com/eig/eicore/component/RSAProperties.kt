package com.eig.eicore.component

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Component
data class RSAProperties(
    @Value("\${jwt.private-key}") val privateKey: RSAPrivateKey,
    @Value("\${jwt.public-key}") val publicKey: RSAPublicKey
)