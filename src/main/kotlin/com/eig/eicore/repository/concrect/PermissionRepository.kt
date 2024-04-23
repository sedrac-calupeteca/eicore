package com.eig.eicore.repository.concrect

import com.eig.eicore.model.concrect.Permission
import com.eig.eicore.repository.ModelConcrectRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PermissionRepository : ModelConcrectRepository<Permission> {
    @Query(name = "SELECT p FROM Permission p WHERE name = :name AND deletedBy IS NULL AND deletedAt is NULL")
    fun findByName(name: String): Optional<Permission>

    @Query(name = "Permission.findByPersonUsername")
    fun findByPersonUsername(username: String): List<Permission>
}