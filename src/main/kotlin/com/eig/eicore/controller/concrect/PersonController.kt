package com.eig.eicore.controller.concrect

import com.eig.eicore.enumeration.ReturnHttpStatus
import com.eig.eicore.model.concrect.Person
import com.eig.eicore.request.post.PersonPostBody
import com.eig.eicore.request.put.PersonPutBody
import com.eig.eicore.seed.PermissionSeed
import com.eig.eicore.service.concrect.PersonService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/persons")
class PersonController(
    val personService: PersonService,
) {

    @GetMapping
    @Operation(tags = ["person"], summary = "list all persons paginated", description = "Use default interface Page of spring for pagination")
    @ApiResponses(value = [ ApiResponse(responseCode = "401", description = "Permission denied") ])
    fun findAll(@ParameterObject pageable: Pageable): ResponseEntity<Page<Person>> {
        personService.canPermission(PermissionSeed.PERSON_VIEW)
        return ResponseEntity(personService.findAll(pageable), ReturnHttpStatus.VIEW)
    }

    @Transactional
    @PostMapping
    @Operation(tags = ["person"], summary = "Create a person", description = "The person is only created if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "201", description = "Person created successfully"),
        ApiResponse(responseCode = "400", description = "Person was not created, read the problem presented"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun save(@Valid @RequestBody personPostBody: PersonPostBody): ResponseEntity<Person>{
        personService.canPermission(PermissionSeed.PERSON_SAVE)
        return ResponseEntity(personService.save(personPostBody.toPerson()), ReturnHttpStatus.CREATED)
    }

    @Transactional
    @PutMapping
    @Operation(tags = ["person"], summary = "Update a person", description = "The person is only updated if it meets all the validations declared in it")
    @ApiResponses(value = [
        ApiResponse(responseCode = "202", description = "Person updated successfully"),
        ApiResponse(responseCode = "404", description = "Person not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun update(@Valid @RequestBody personPutBody: PersonPutBody): ResponseEntity<Person> {
        personService.canPermission(PermissionSeed.PERSON_UPDATE)
        return ResponseEntity(personService.update(personPutBody.toPerson()), ReturnHttpStatus.UPDATED)
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(tags = ["person"], summary = "Delete a person", description = "The person is only deleted if they provide an id that exists")
    @ApiResponses(value = [
        ApiResponse(responseCode = "204", description = "Person deleted successfully"),
        ApiResponse(responseCode = "404", description = "Person not found"),
        ApiResponse(responseCode = "401", description = "Permission denied")
    ])
    fun deleteById(@PathVariable id: String): ResponseEntity<Unit> {
        personService.canPermission(PermissionSeed.PERSON_DELETE)
        return ResponseEntity(personService.deleteById(id), ReturnHttpStatus.DELETED)
    }

}