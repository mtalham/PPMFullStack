package fullstack.development.ppmtool.validator

import fullstack.development.ppmtool.domain.User
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator;

@Component
class UserValidator: Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return User::class == clazz
    }

    override fun validate(target: Any, errors: Errors) {
       val user = target as User
        if (user.password.length < 6) {
            errors.rejectValue("password", "Length", "Password must be at least 6 characters")
        }
        if (!(user.confirmPassword.equals(user.password))) {
            errors.rejectValue("confirmPassword", "Match", "Passwords must match")
        }
    }

}