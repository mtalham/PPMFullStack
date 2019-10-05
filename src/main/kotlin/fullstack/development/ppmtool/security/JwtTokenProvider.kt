package fullstack.development.ppmtool.security

import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.security.SecurityConstants.EXPIRATION_TIME
import fullstack.development.ppmtool.security.SecurityConstants.SECRET
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.SignatureException
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.Date

@Component
class JwtTokenProvider {
    // Generate the token
    fun generateToken(authentication: Authentication): String {
        val user = authentication.principal as User
        val now = Date(System.currentTimeMillis())
        val userId = user.id.toString()

        val claims: MutableMap<String, String> = HashMap()
        claims["id"] = userId
        claims["username"] = user.username
        claims["fullName"] = user.fullName

        return Jwts.builder().setSubject(userId)
            .setClaims(claims as Map<String, Any>).setIssuedAt(now)
            .setExpiration(Date(now.time + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
    }

    //Validate the token
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token)
            return true
        } catch (ex: SignatureException) {
            println("Invalid JWT Signature: $ex")
        } catch (ex: MalformedJwtException) {
            println("Invalid JWT Token: $ex")
        } catch (ex: JwtException) {
            println("Expired JWT Token: $ex")
        } catch (ex: UnsupportedJwtException) {
            println("Unsupported JWT Token: $ex")
        } catch (ex: IllegalArgumentException) {
            println("JWT claims string is empty: $ex")
        }
        return false
    }

    //Get userId from the token
    fun getUserIdFromToken(token: String): Long {
        val claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).body
        val id = claims["id"] as String
        return id.toLong()
    }
}