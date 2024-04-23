package com.eig.eicore.repository.concrect

import com.eig.eicore.model.concrect.Role
import com.eig.eicore.repository.ModelConcrectRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository : ModelConcrectRepository<Role> {
    @Query(name = "SELECT r FROM Role r WHERE name = :name AND deletedBy IS NULL AND deletedAt is NULL")
    fun findByName(name: String): Optional<Role>
}