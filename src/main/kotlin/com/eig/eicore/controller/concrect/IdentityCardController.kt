package com.eig.eicore.controller.concrect

import com.eig.eicore.enumeration.ActionBody
import com.eig.eicore.enumeration.ReturnHttpStatus
import com.eig.eicore.model.concrect.IdentityCard
import com.eig.eicore.request.IdentityCardBody
import com.eig.eicore.seed.PermissionSeed
import com.eig.eicore.service.concrect.IdentityCardService
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
@RequestMapping("/identity-cards")
@CrossOrigin("*")
class IdentityCardController(
    val identityCardService: IdentityCardService,
) {

    @GetMapping
    @Operation(tags = ["identity-cards"], summary = "list all identity cards paginated", description = "Use default interface Page of spring for pagination")
    @ApiResponses(value = [ ApiResponse(responseCode = "401", description = "Permission denied") ])
    fun findAll(@ParameterObject pageable: Pageable): ResponseEntity<Page<IdentityCard>> {
        identityCardService.canPermission(PermissionSeed.IDENTITY_CARD_VIEW)
        return ResponseEntity(identityCardService.findAll(pageable), ReturnHttpStatus.VIEW)
    }

    @Transactional
    @PostMapping
    @Operation(tags = ["identity-cards"], summary = "Create a identity card", description = "The identity card is only created if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Identity card created successfully"),
        ApiResponse(responseCode = "400", description = "Identity card was not created, read the problem presented"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
        fun save(@Valid @RequestBody identityCardBody: IdentityCardBody): ResponseEntity<IdentityCard> {
        identityCardService.canPermission(PermissionSeed.IDENTITY_CARD_SAVE)
        return ResponseEntity(identityCardService.save(identityCardBody.toEntity()), ReturnHttpStatus.CREATED)
    }

    @Transactional
    @PutMapping
    @Operation(tags = ["identity-cards"], summary = "Update a identity card", description = "The identity card is only updated if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Identity card updated successfully"),
        ApiResponse(responseCode = "404", description = "Identity card not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun update(@Valid @RequestBody identityCardBody: IdentityCardBody): ResponseEntity<IdentityCard> {
        identityCardService.canPermission(PermissionSeed.IDENTITY_CARD_UPDATE)
        return ResponseEntity(identityCardService.update(identityCardBody.toEntity(ActionBody.UPDATE)), ReturnHttpStatus.UPDATED)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(tags = ["identity-cards"], summary = "Delete a identity card", description = "The identity card is only deleted if they provide an id that exists")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Identity card  deleted successfully"),
        ApiResponse(responseCode = "404", description = "Identity card  not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun deleteById(@PathVariable id: String): ResponseEntity<Unit> {
        identityCardService.canPermission(PermissionSeed.IDENTITY_CARD_DELETE)
        return ResponseEntity(identityCardService.deleteById(id), ReturnHttpStatus.DELETED)
    }

}