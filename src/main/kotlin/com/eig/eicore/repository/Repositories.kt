package com.eig.eicore.repository

import com.eig.eicore.model.ModelCommon
import com.eig.eicore.model.ModelConcrect
import com.eig.eicore.model.concrect.Person
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.time.LocalDateTime
import java.util.*

@NoRepositoryBean
interface  ModelCommonRepository <T: ModelCommon> : JpaRepository<T, String>

@NoRepositoryBean
interface  ModelConcrectRepository <T: ModelConcrect> : ModelCommonRepository<T>{

    @Query(name = "ModelConcrect.findAll", countName = "ModelConcrect.findAllCount")
    override fun findAll(pageable: Pageable): Page<T>

    @Query(name = "ModelConcrect.findById")
    override fun findById(id: String): Optional<T>

}

