package com.eig.eicore.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotEmpty

data class LoginBody(
    @NotEmpty
    @Schema(description = "Username for authentication")
    val username: String,
    @NotEmpty
    @Schema(description = "Password for authentication")
    val password: String
)
