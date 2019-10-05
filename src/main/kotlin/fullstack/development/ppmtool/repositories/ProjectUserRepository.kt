package fullstack.development.ppmtool.repositories

import fullstack.development.ppmtool.domain.ProjectUser
import org.springframework.data.repository.CrudRepository

interface ProjectUserRepository: CrudRepository<ProjectUser, Long> {
    fun findByProjectId(projectId: Long): List<ProjectUser>

    fun findByUserId(userId: Long): List<ProjectUser>
}