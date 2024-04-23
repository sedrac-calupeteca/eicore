package com.eig.eicore.request

import com.eig.eicore.builder.concrect.PermissionBuilder
import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.exception.BadRequestDataException
import com.eig.eicore.interfaces.IBody
import com.eig.eicore.model.concrect.Permission
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import java.util.*

data class PermissionBody(
    var id: String? = null,
    @NotEmpty
    @Pattern(regexp = "^\\w+")
    @Schema(description = "This is Permission`s name must meet this regex ^\\w+", example = "PERMISSION_VIEW_ROLE")
    var name: String,
    @NotEmpty
    @Pattern(regexp = "^\\w+(\\s\\w+)+")
    @Schema(description = "This is Permission`s description must meet this regex ^\\w+(\\s\\w+)+", example = "Permission to view positions")
    var description: String
): IBody<Permission> {

    override fun toEntity(action: ActionBody) : Permission {
        val permission = PermissionBuilder().name(name).description(description).build()
        return when(action){
            ActionBody.UPDATE -> permission.apply { id = Optional.ofNullable(this@PermissionBody.id).orElseThrow { BadRequestDataException() } }
            else -> permission
        }
    }

}