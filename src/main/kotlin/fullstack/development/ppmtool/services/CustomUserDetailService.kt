package fullstack.development.ppmtool.services

import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.exceptions.ProjectException
import fullstack.development.ppmtool.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByUsername(username) ?: throw ProjectException("User: $username does not exist")
    }

    fun loadUserById(id: Long): User = userRepository.getById(id)
        ?: throw ProjectException("User with ID: '$id' does not exist")
}