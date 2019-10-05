package fullstack.development.ppmtool.security

import java.time.Duration

object SecurityConstants {
    const val SIGN_UP_URLS = "/api/users/**"
    const val GRAPIHQL_URL = "/api/graphiql/**"
    const val H2_URL = "h2-console/**"
    const val SECRET = "SecretKeyToGenJWTs"
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    val EXPIRATION_TIME: Long = 60.minutes
}

val Int.minutes: Long
    get() {
        return Duration.ofMinutes(this.toLong()).toMillis()
    }