package fullstack.development.ppmtool.web

import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.payload.JWTlLoginSuccessResponse
import fullstack.development.ppmtool.payload.LoginRequest
import fullstack.development.ppmtool.security.JwtTokenProvider
import fullstack.development.ppmtool.security.SecurityConstants.TOKEN_PREFIX
import fullstack.development.ppmtool.services.MapErrorValidationService
import fullstack.development.ppmtool.services.UserService
import fullstack.development.ppmtool.validator.UserValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val userValidator: UserValidator,
    private val mapErrorValidationService: MapErrorValidationService,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManager: AuthenticationManager
) {
    @PostMapping("/login")
    fun authenticateUser(@RequestBody loginRequest: LoginRequest, result: BindingResult): ResponseEntity<*> {
        val errorMap = mapErrorValidationService.mapValidationService(result)
        if (errorMap != null) return errorMap

        val authenticate = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authenticate
        val jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authenticate)
        return ResponseEntity.ok(JWTlLoginSuccessResponse(true, jwt))
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody user: User, result: BindingResult): ResponseEntity<*> {
        userValidator.validate(user, result)
        val errorMap = mapErrorValidationService.mapValidationService(result)
        if (errorMap != null) return errorMap

        val registerUser = userService.registerUser(user)
        return ResponseEntity(registerUser, HttpStatus.CREATED)
    }
}