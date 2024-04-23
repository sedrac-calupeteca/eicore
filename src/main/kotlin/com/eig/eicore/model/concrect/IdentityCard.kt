package com.eig.eicore.model.concrect

import com.eig.eicore.model.ModelConcrect
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name="tb_identity_card")
class IdentityCard(
    @JsonIgnore @ManyToOne var person: Person,
    @Column(nullable = false) var residential: String,
    @Column(nullable = false) var emittedIn: String,
    @Column(nullable = false) var emittedAt: LocalDate,
    @Column(nullable = false) var validAt: LocalDate,
) : ModelConcrect(){

    constructor(): this(Person(),"","",LocalDate.now(),LocalDate.now())

    override fun toString(): String = "IdentityCard(person=$person, residential='$residential', emittedIn='$emittedIn', emittedAt=$emittedAt, validAt=$validAt)"

    override fun concatValuesFields() = setConcatFields("${person.id},$residential,$emittedIn,$emittedAt,$validAt")

}