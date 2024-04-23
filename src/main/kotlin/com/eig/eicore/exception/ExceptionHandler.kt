package com.eig.eicore.exception

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.Optional

@RestControllerAdvice
class ExceptionHandler {

    private fun <T: Exception> exception  (exception: T, httpStatusCode: HttpStatus, detail: String? = null): ResponseEntity<ProblemDetail> {
        val problemDetail = ProblemDetail.forStatus(httpStatusCode)
        problemDetail.title = exception.message
        Optional.ofNullable(detail).ifPresent { problemDetail.detail = detail }
        return ResponseEntity.status(httpStatusCode).body(problemDetail)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<ProblemDetail>{
        val fieldsErrors = exception.bindingResult.fieldErrors.map { FieldError::getField }.joinToString { ";" }
        val fieldsErrorsMessage = exception.bindingResult.fieldErrors.map { FieldError::getDefaultMessage }.joinToString { ";" }
        val detail = "[fieldsErrors=$fieldsErrors | fieldsErrorsMessage=$fieldsErrorsMessage]"
        return exception(exception, HttpStatus.BAD_REQUEST, detail)
    }

    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handlerDataIntegrityViolationException(exception: DataIntegrityViolationException): ResponseEntity<ProblemDetail> {
        val httpStatusCode = HttpStatus.CONFLICT
        val problemDetail = ProblemDetail.forStatus(httpStatusCode)
        problemDetail.title = exception.message?.substringBefore("]")+"]"
        return ResponseEntity.status(httpStatusCode).body(problemDetail)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handlerHttpMessageNotReadableException(exception: HttpMessageNotReadableException): ResponseEntity<ProblemDetail> = exception(exception, HttpStatus.UNAUTHORIZED)

    @ExceptionHandler(BadCredentialsException::class)
    fun handlerBadCredentialsException(exception: BadCredentialsException): ResponseEntity<ProblemDetail> = exception(exception, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(BadRequestDataException::class)
    fun handlerBadRequestException(exception: BadRequestDataException): ResponseEntity<ProblemDetail> = exception(exception, HttpStatus.CONFLICT)

    @ExceptionHandler(NotFoundResourceException::class)
    fun handlerNotFoundException(exception: NotFoundResourceException): ResponseEntity<ProblemDetail> = exception(exception, HttpStatus.UNPROCESSABLE_ENTITY)

    @ExceptionHandler(PermissionDeniedException::class)
    fun handlerPermissionDeniedException(exception: PermissionDeniedException): ResponseEntity<ProblemDetail> = exception(exception, HttpStatus.UNAUTHORIZED)

}