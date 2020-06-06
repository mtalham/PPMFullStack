package fullstack.development.ppmtool.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ProjectException(message: String) : RuntimeException(message)

fun String.validateNotBlank(msg: String) {
  require(this.isNotBlank()) { throw ProjectException(msg) }
}

inline fun validate(condition: Boolean, lazyMessage: () -> Any) {
  require(condition) { throw ProjectException(lazyMessage().toString()) }
}
