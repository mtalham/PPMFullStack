package fullstack.development.ppmtool.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
@RestController
class CustomResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {
    @ExceptionHandler
    fun handleProjectIdException(ex: ProjectException, req: WebRequest): ResponseEntity<String?> {
        return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
    }
}