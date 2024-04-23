package com.eig.eicore.request.post

import com.eig.eicore.builder.concrect.PersonBuilder
import com.eig.eicore.enumeration.Gender
import com.eig.eicore.enumeration.MaritalStatus
import com.eig.eicore.model.concrect.Person
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.util.Optional

data class PersonPostBody(
    @Pattern(regexp = "^\\w+(\\s\\w+)+", message = "{error.fullname}")
    @Schema(description = "This is the Person`s fullname must meet this regex ^\\w+(\\s\\w+)+", example = "John Guy Silver")
    var fullname: String,
    @Pattern(regexp = "^\\w+(\\s\\w+)+", message = "{error.fullnameFather}")
    @Schema(description = "This is Person`s fullname of father must meet this regex ^\\w+(\\s\\w+)+", example = "Obama Silver")
    var fullnameFather: String,
    @Pattern(regexp = "^\\w+(\\s\\w+)+", message = "{error.fullnameMother}")
    @Schema(description = "This is Person`s fullname of mother must meet this regex ^\\w+(\\s\\w+)+", example = "Bella Guy")
    var fullnameMother: String,
    @Pattern(regexp = "^\\d{9}[A-Z][A-Z]\\d{3}", message = "{error.identityCardNumber}")
    @Schema(description = "This is the person's identity card number must meet this regex ^\\d{9}[A-Z][A-Z]\\d{3}", example = "005610433EG023")
    var identityCardNumber: String,
    @Pattern(regexp = "^\\w+")
    @Schema(description = "This is Person`s username")
    var username: String,
    @Email
    @Schema(description = "This is Person`s email")
    var email: String,
    @Schema(description = "This is Person`s username")
    var naturalFrom: String = "",
    @Schema(description = "This is Person`s gender [MALE, FEMALE]")
    var gender: Gender,
    @Schema(description = "This is Person`s marital status [MARRIED, SINGLE, DIVORCED, WIDOWER]")
    var maritalStatus: MaritalStatus = MaritalStatus.SINGLE,
    @NotNull
    @Schema(description = "This is Person`s password")
    var password: String,
    @NotNull
    @Schema(description = "This is the person's birthday and the date format is yyyy-mm-dd")
    var birthday: LocalDate,
) {

    fun toPerson(id: String? = null) : Person {
       val person = PersonBuilder()
            .fullname(fullname)
            .fullnameFather(fullnameFather)
            .fullnameMother(fullnameMother)
            .identityCardNumber(identityCardNumber)
            .username(username)
            .email(email)
            .gender(gender)
            .maritalStatus(maritalStatus)
            .password(password)
            .naturalFrom(naturalFrom)
            .birthday(birthday)
            .build()
        Optional.ofNullable(id).ifPresent { person.id = it }
        return  person
    }

}