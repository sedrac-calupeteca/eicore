package com.eig.eicore.controller.concrect

import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.enumeration.ReturnHttpStatus
import com.eig.eicore.model.concrect.Role
import com.eig.eicore.request.RoleBody
import com.eig.eicore.seed.PermissionSeed
import com.eig.eicore.service.concrect.RoleService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/roles")
@CrossOrigin("*")
class RoleController(
    val roleService: RoleService,
) {

    @GetMapping
    @Operation(tags = ["role"], summary = "list all roles paginated", description = "Use default interface Page of spring for pagination")
    @ApiResponses(value = [
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun findAll(@ParameterObject pageable: Pageable): ResponseEntity<Page<Role>> {
        roleService.canPermission(PermissionSeed.ROLE_VIEW)
        return ResponseEntity(roleService.findAll(pageable), ReturnHttpStatus.VIEW)
    }

    @Transactional
    @PostMapping
    @Operation(tags = ["role"], summary = "Create a role", description = "The role is only created if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Role created successfully"),
        ApiResponse(responseCode = "400", description = "Role was not created, read the problem presented"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun save(@Valid @RequestBody roleBody: RoleBody): ResponseEntity<Role> {
        roleService.canPermission(PermissionSeed.ROLE_SAVE)
        return ResponseEntity(roleService.save(roleBody.toEntity()), ReturnHttpStatus.CREATED)
    }

    @Transactional
    @PutMapping
    @Operation(tags = ["role"], summary = "Update a role", description = "The role is only updated if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Role updated successfully"),
        ApiResponse(responseCode = "404", description = "Role not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun update(@Valid @RequestBody roleBody: RoleBody): ResponseEntity<Role> {
        roleService.canPermission(PermissionSeed.ROLE_UPDATE)
        return ResponseEntity(roleService.update(roleBody.toEntity(ActionBody.UPDATE)), ReturnHttpStatus.UPDATED)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(tags = ["role"], summary = "Delete a role", description = "The role is only deleted if they provide an id that exists")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Role deleted successfully"),
        ApiResponse(responseCode = "404", description = "Role not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun deleteById(@PathVariable id: String): ResponseEntity<Unit> {
        roleService.canPermission(PermissionSeed.ROLE_DELETE)
        return ResponseEntity(roleService.deleteById(id), ReturnHttpStatus.DELETED)
    }

}