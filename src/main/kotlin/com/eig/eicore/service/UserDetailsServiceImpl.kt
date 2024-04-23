package com.eig.eicore.service

import com.eig.eicore.repository.concrect.PermissionRepository
import com.eig.eicore.repository.concrect.PersonRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
@Transactional
class UserDetailsServiceImpl(
    val personRepository: PersonRepository,
    val permissionRepository: PermissionRepository
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val name: String = Optional.ofNullable(username).orElseThrow{ throw BadCredentialsException("Inform username") }
        val person = personRepository.findByUsername(name).orElseThrow { throw BadCredentialsException("Username not found: $username") }
        person.privileges = permissionRepository.findByPersonUsername(person.username).map { SimpleGrantedAuthority(it.name) }.toMutableList()
        return User(person.id, person.password,  person.isEnabled, person.isAccountNonExpired, person.isCredentialsNonExpired, person.isAccountNonLocked, person.privileges)
    }

}