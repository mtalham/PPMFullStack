package fullstack.development.ppmtool.security

import com.google.gson.Gson
import fullstack.development.ppmtool.exceptions.InvalidLoginResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationEntryPoint: AuthenticationEntryPoint {
    override fun commence(request: HttpServletRequest?, response: HttpServletResponse, authException: AuthenticationException?) {
        val loginResponse = InvalidLoginResponse()
        val jsonLoginResponse = Gson().toJson(loginResponse)

        response.contentType = "application/json"
        response.status = 401
        response.writer.print(jsonLoginResponse)
    }
}