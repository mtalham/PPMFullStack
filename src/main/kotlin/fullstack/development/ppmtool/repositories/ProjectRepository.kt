package fullstack.development.ppmtool.repositories

import fullstack.development.ppmtool.domain.Project
import org.springframework.data.repository.CrudRepository

interface ProjectRepository: CrudRepository<Project, Long> {
    fun findProjectByProjectIdentifier(projectId: String): Project?

    fun findProjectByProjectIdentifierAndProjectLeader(projectId: String, userName: String): Project?

    override fun findAll(): List<Project>
}