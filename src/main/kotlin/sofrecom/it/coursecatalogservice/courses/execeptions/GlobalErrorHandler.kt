package sofrecom.it.coursecatalogservice.courses.execeptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Component
@ControllerAdvice
class GlobalErrorHandler : ResponseEntityExceptionHandler(){

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errors = ex.bindingResult.allErrors
            .map {
                error -> error.defaultMessage
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.joinToString(","))
    }

    @ExceptionHandler(Exception::class)
    fun handelAllExceptions(ex: Exception, request: WebRequest):ResponseEntity<Any>{
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ex.message)
    }

}