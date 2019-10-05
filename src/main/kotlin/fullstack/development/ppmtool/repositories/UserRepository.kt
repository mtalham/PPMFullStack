package fullstack.development.ppmtool.repositories

import fullstack.development.ppmtool.domain.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByUsername(username: String): User?

    fun getById(id: Long): User?
    override fun findAll(): List<User>
}