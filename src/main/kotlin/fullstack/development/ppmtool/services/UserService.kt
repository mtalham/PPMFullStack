package fullstack.development.ppmtool.services

import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.exceptions.ProjectException
import fullstack.development.ppmtool.repositories.UserRepository
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val bCryptPasswordEncoder: BCryptPasswordEncoder) {
    fun registerUser(user: User): User {
        require(user.fullName.isNotBlank()) { throw ProjectException("Name cannot be blank") }
        val newUser = User(
            user.username, user.fullName,
            bCryptPasswordEncoder.encode(user.password),
            bCryptPasswordEncoder.encode(user.confirmPassword)
        )

        try {
            return userRepository.save(newUser)
        } catch (e: DataIntegrityViolationException) {
//            val err = e.cause.localizedMessage { cv -> throw ProjectException("${cv.propertyPath}: ${cv.messageTemplate}") }.toString()
            throw ProjectException("username: ${user.username} already exist")
        }
    }

    fun getUsers() : List<User> =
        userRepository.findAll()

    fun getUserByUsername(username: String): User =
        userRepository.findByUsername(username)
            ?: throw ProjectException("unable to find user: $username")

}