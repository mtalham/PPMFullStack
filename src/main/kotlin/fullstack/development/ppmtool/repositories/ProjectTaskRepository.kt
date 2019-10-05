package fullstack.development.ppmtool.repositories

import fullstack.development.ppmtool.domain.ProjectTask
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectTaskRepository: CrudRepository<ProjectTask, Long> {
    fun findByProjectIdentifierOrderByPriority(identifier: String): List<ProjectTask>?

    fun findByProjectSequence(sequence: String): ProjectTask?
}