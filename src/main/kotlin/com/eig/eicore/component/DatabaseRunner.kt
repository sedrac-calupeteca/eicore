package com.eig.eicore.component


import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.seed.*
import com.eig.eicore.service.concrect.PermissionService
import com.eig.eicore.service.concrect.PersonService
import com.eig.eicore.service.concrect.RoleService
import com.eig.eicore.service.pivot.RolePermissionService
import com.eig.eicore.service.pivot.RolePersonService
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class DatabaseRunner (
    val roleService: RoleService,
    val personService: PersonService,
    val permissionService: PermissionService,
    val rolePersonService: RolePersonService,
    val rolePermissionService: RolePermissionService
): ApplicationRunner{

    override fun run(args: ApplicationArguments?) {

        val roles: MutableList<Role> = mutableListOf()
        val persons: MutableList<Person> = mutableListOf()
        val permissions: MutableList<Permission> = mutableListOf()

        RoleSeed.entries.forEach { roles.addLast(roleService.saveOrUpdate(it.role))  }
        PersonSeed.entries.forEach { persons.addLast(personService.saveOrUpdate(it.person)) }
        PermissionSeed.entries.forEach { permissions.addLast(permissionService.saveOrUpdate(it.permission))  }

        RolePermissionSeed.entries.forEach {
            it.role = roles.first { p -> p.name == it.role.name }
            it.permission = permissions.first { p -> p.name == it.permission.name }
            rolePermissionService.saveOrUpdate(it.toRolePermission())
        }

        RolePersonSeed.entries.forEach {
            it.role = roles.first { p -> p.name == it.role.name }
            it.person = persons.first { p -> p.username == it.person.username}
            rolePersonService.saveOrUpdate(it.toRolePerson())
        }

    }

}