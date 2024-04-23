package com.eig.eicore.seed

import com.eig.eicore.factory.RoleFactory
import com.eig.eicore.model.concrect.Role

enum class RoleSeed(val role: Role) {
    SUPER(RoleFactory.build("SUPER", "Permissão total")),
    ADMIN(RoleFactory.build("ADMIN", "Permissões para super administradores")),
    DEV(RoleFactory.build("DEV", "Permissões para programadores")),
    ROLE(RoleFactory.build("ROLE", "Administrador de cargos")),
    PERMISSION(RoleFactory.build("PERMISSION", "Administrador de permissões")),
    PERSON(RoleFactory.build("PERSON", "Administrador de utilizadores")),
    IDENTITY_CARD(RoleFactory.build("IDENTITY_CARD", "Administrador de bilhete de identidade")),
}