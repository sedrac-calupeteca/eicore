package com.eig.eicore.request

import com.eig.eicore.builder.concrect.IdentityCardBuilder
import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.exception.BadRequestDataException
import com.eig.eicore.interfaces.IBody
import com.eig.eicore.model.concrect.IdentityCard
import com.eig.eicore.model.concrect.Person
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import java.time.LocalDate
import java.util.*

data class IdentityCardBody(
    var id: String? = null,
    @NotEmpty
    @Pattern(regexp = "^\\w+(\\s\\w+)+")
    @Schema(description = "Place where the identity card was issued, must meet this regex ^\\w+(\\s\\w+)+", example = "Luanda")
    var emittedIn: String,
    @NotEmpty
    @Pattern(regexp = "^\\w+(\\s\\w+)+")
    @Schema(description = "The residence of the person to whom the identity card belongs, must meet this regex ^\\w+(\\s\\w+)+", example = "Cazenga")
    var residential: String,
    @NotEmpty
    @Schema(description = "Inform the date of issue of the identity card", example = "2020-12-12")
    var emittedAt: LocalDate,
    @NotEmpty
    @Schema(description = "Inform the expiration date of the identity card", example = "2030-12-10")
    var validAt: LocalDate,
    @NotEmpty
    @Schema(description = "Inform the person to whom the identity card belongs")
    var person: Person,
): IBody<IdentityCard> {

    override fun toEntity(action: ActionBody): IdentityCard {
         val identityCard =  IdentityCardBuilder()
             .emittedAt(emittedAt).validAt(validAt).emittedIn(emittedIn).residential(residential).person(person)
             .build()
        return when(action){
            ActionBody.UPDATE -> identityCard.apply { id = Optional.ofNullable(this@IdentityCardBody.id).orElseThrow { BadRequestDataException() } }
            else -> identityCard
        }
     }

}