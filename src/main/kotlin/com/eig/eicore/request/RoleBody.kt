package com.eig.eicore.request

import com.eig.eicore.builder.concrect.RoleBuilder
import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.enumeration.RoleType
import com.eig.eicore.exception.BadRequestDataException
import com.eig.eicore.interfaces.IBody
import com.eig.eicore.model.concrect.Role
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import java.util.*

data class RoleBody(
    var id: String? = null,
    @NotEmpty
    @Pattern(regexp = "^\\w+")
    @Schema(description = "This is Role`s name must meet this regex ^\\w+", example = "ROLE_ADMIN")
    var name: String,
    @NotEmpty
    @Pattern(regexp = "^\\w+(\\s\\w+)+")
    @Schema(description = "This is Role`s description must meet this regex ^\\w+(\\s\\w+)+", example = "This position allows the actions that an administrator can have")
    var description: String,
    var roleType: RoleType = RoleType.APPLICATION,
): IBody<Role> {

    override fun toEntity(action: ActionBody) : Role {
        val role =  RoleBuilder().name(name).description(description).build()
        return when(action){
            ActionBody.UPDATE -> role.apply { id = Optional.ofNullable(this@RoleBody.id).orElseThrow { BadRequestDataException() } }
            else -> role
        }
    }

}