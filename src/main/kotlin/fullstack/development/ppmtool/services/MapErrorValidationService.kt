package fullstack.development.ppmtool.services

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError

@Service
class MapErrorValidationService {
    fun mapValidationService(result: BindingResult): ResponseEntity<Any>? {
        if (result.hasErrors()) {
            val errorMap: MutableMap<String, String> = HashMap()
            for (error: FieldError in result.fieldErrors) {
                errorMap[error.field] = error.defaultMessage ?: ""
            }
            return ResponseEntity(errorMap, HttpStatus.BAD_REQUEST)
        }
        return null
    }
}