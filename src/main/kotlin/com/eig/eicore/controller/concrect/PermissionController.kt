package com.eig.eicore.controller.concrect

import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.enumeration.ReturnHttpStatus
import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.request.PermissionBody
import com.eig.eicore.seed.PermissionSeed
import com.eig.eicore.service.concrect.PermissionService
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
@RequestMapping("/api/v1/permissions")
@CrossOrigin("*")
class PermissionController(
    val permissionService: PermissionService,
) {

    @GetMapping
    @Operation(tags = ["permission"], summary = "list all permissions paginated", description = "Use default interface Page of spring for pagination")
    @ApiResponses(value = [ ApiResponse(responseCode = "401", description = "Permission denied") ])
    fun findAll(@ParameterObject pageable: Pageable): ResponseEntity<Page<Permission>> {
        permissionService.canPermission(PermissionSeed.PERMISSION_VIEW)
        return ResponseEntity(permissionService.findAll(pageable), ReturnHttpStatus.VIEW)
    }

    @Transactional
    @PostMapping
    @Operation(tags = ["permission"], summary = "Create a permission", description = "The permission is only created if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Permission created successfully"),
        ApiResponse(responseCode = "400", description = "Permission was not created, read the problem presented"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun save(@Valid @RequestBody permissionBody: PermissionBody): ResponseEntity<Permission> {
        permissionService.canPermission(PermissionSeed.PERMISSION_SAVE)
        return ResponseEntity(permissionService.save(permissionBody.toEntity()), ReturnHttpStatus.CREATED)
    }

    @Transactional
    @PutMapping
    @Operation(tags = ["permission"], summary = "Update a permission", description = "The permission is only updated if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Permission updated successfully"),
        ApiResponse(responseCode = "404", description = "Permission not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun update(@Valid @RequestBody permissionBody: PermissionBody): ResponseEntity<Permission> {
        permissionService.canPermission(PermissionSeed.PERMISSION_UPDATE)
        return ResponseEntity(permissionService.update(permissionBody.toEntity(ActionBody.UPDATE)),  ReturnHttpStatus.UPDATED)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(tags = ["permission"], summary = "Delete a permission", description = "The permission is only deleted if they provide an id that exists")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Permission deleted successfully"),
        ApiResponse(responseCode = "404", description = "Permission not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun deleteById(@PathVariable id: String): ResponseEntity<Unit> {
        permissionService.canPermission(PermissionSeed.PERMISSION_DELETE)
        return ResponseEntity(permissionService.deleteById(id),  ReturnHttpStatus.DELETED)
    }

}