package com.eig.eicore.controller

import com.eig.eicore.request.LoginBody
import com.eig.eicore.service.TokenService
import com.eig.eicore.service.concrect.PersonService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/login")
class LoginController(
    val tokenService: TokenService,
    val personService: PersonService,
    val passwordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/jwt")
    @Operation(tags = ["auth"], summary = "generated jwt", description = "Provide credentials (username, password)")
    fun token(@RequestBody loginRequest: LoginBody): ResponseEntity<String> {
        val person = personService.findByUsername(loginRequest.username)
        if(!passwordEncoder.matches(loginRequest.password, person.password)) throw BadCredentialsException("Password failed")
        return ResponseEntity.ok(tokenService.generatorToken(person))
    }

}