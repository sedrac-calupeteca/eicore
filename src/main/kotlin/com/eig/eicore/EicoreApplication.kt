package com.eig.eicore

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cache.annotation.EnableCaching

@OpenAPIDefinition
@EnableCaching
@SpringBootApplication
class EicoreApplication

fun main(args: Array<String>) {
	runApplication<EicoreApplication>(*args)
}
