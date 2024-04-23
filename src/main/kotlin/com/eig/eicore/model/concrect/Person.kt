package com.eig.eicore.model.concrect

import com.eig.eicore.enumeration.Gender
import com.eig.eicore.enumeration.MaritalStatus
import com.eig.eicore.model.ModelConcrect
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDate
import kotlin.jvm.Transient

@Entity
@Table(name ="tb_persons")
class Person(
    @Column(nullable = false) var fullname: String,
    @Column(nullable = false) var fullnameFather: String,
    @Column(nullable = false) var fullnameMother: String,
    @Column(unique = true) var identityCardNumber: String,
    @JvmField @Column(unique = true) var username: String,
    @Column(unique = true) var email: String,
    @JvmField @Column(nullable = false) var password: String,
    @Column(nullable = false) var naturalFrom: String,
    @Column(nullable = false) var birthday: LocalDate,
    @Enumerated(EnumType.STRING) var gender: Gender = Gender.MALE,
    @Enumerated(EnumType.STRING) var maritalStatus: MaritalStatus = MaritalStatus.SINGLE,
    @JvmField var isAccountNonExpired: Boolean = true,
    @JvmField var isAccountNonLocked: Boolean = true,
    @JvmField var isCredentialsNonExpired: Boolean = true,
    @JvmField var isEnabled: Boolean = true,
    @Transient var privileges: MutableList<GrantedAuthority> = mutableListOf()
): ModelConcrect(), UserDetails{

    constructor() : this("","","","","","","","",LocalDate.now())

    @PrePersist
    fun prePersistPerson(){ password = BCryptPasswordEncoder().encode(password) }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = privileges

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = isAccountNonExpired

    override fun isAccountNonLocked(): Boolean = isAccountNonLocked

    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired

    override fun isEnabled(): Boolean = isEnabled

    override fun concatValuesFields() = setConcatFields("$fullname,$fullnameFather,$fullnameMother,$identityCardNumber,$username,$email,$gender,$maritalStatus,$birthday,$naturalFrom")

    override fun toString(): String = "Person(fullname='$fullname', fullnameFather='$fullnameFather', fullnameMother='$fullnameMother', identityCardNumber='$identityCardNumber', username='$username', email='$email', gender=$gender, maritalStatus=$maritalStatus, password='$password', birthday=$birthday, naturalFrom='$naturalFrom', isAccountNonExpired=$isAccountNonExpired, isAccountNonLocked=$isAccountNonLocked, isCredentialsNonExpired=$isCredentialsNonExpired, isEnabled=$isEnabled)"


}