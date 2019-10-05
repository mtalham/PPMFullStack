package fullstack.development.ppmtool.web

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller

interface IAuthenticationFacade {
    fun authentication(): Authentication
}

@Component
class AuthenticationFacade: IAuthenticationFacade {
    override fun authentication(): Authentication = SecurityContextHolder.getContext().authentication
}

@Controller
class SecurityController(private val authenticationFacade: IAuthenticationFacade) {
    fun currentUserName(): String = authenticationFacade.authentication().name
}