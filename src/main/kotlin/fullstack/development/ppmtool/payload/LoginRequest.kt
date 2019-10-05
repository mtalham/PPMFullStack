package fullstack.development.ppmtool.payload

data class LoginRequest(val username: String, val password: String)

data class JWTlLoginSuccessResponse(
    val success: Boolean,
    val token: String
)