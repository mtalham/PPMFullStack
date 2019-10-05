package fullstack.development.ppmtool.security

import fullstack.development.ppmtool.security.SecurityConstants.HEADER_STRING
import fullstack.development.ppmtool.security.SecurityConstants.TOKEN_PREFIX
import fullstack.development.ppmtool.services.CustomUserDetailService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.Exception

@Component
class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider,
    private val customUserDetailService: CustomUserDetailService
) : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val jwt = getJWTFromRequest(request)
            if (jwt != null && jwtTokenProvider.validateToken(jwt)) {
                val user = customUserDetailService.loadUserById(jwtTokenProvider.getUserIdFromToken(jwt))
                val authentication = UsernamePasswordAuthenticationToken(user, null, emptyList())

                authentication.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (ex: Exception) {
            logger.error("unable to set user authentication in security context", ex)
        }
        filterChain.doFilter(request, response)
    }

    private fun getJWTFromRequest(request: HttpServletRequest): String? {
        val header = request.getHeader(HEADER_STRING)
        if (header.isNotEmpty() && header.startsWith(TOKEN_PREFIX)) {
            return header.substring(7, header.length)
        }
        return null
    }
}