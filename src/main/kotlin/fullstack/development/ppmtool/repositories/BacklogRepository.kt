package fullstack.development.ppmtool.repositories

import fullstack.development.ppmtool.domain.Backlog
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BacklogRepository: CrudRepository<Backlog, Long> {
    fun findByProjectIdentifier(projectIdentifier: String): Backlog?
}