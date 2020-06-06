package fullstack.development.ppmtool.services

import fullstack.development.ppmtool.domain.ProjectUser
import fullstack.development.ppmtool.repositories.ProjectUserRepository
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Service

@Service
class ProjectUserService(
    private val userService: UserService,
    @Lazy private val projectService: ProjectService,
    private val projectUserRepository: ProjectUserRepository
) {
    fun addUserToProject(username: String, projectId: String): Boolean {
        val user = userService.getUserByUsername(username)
        val project = projectService.getProjectIdByProjectIdentifier(projectId)

        projectUserRepository.save(ProjectUser(user.id, project))
        return true
    }

    fun getProjectUserByUserId(userId: Long) = projectUserRepository.findByUserId(userId)

    fun getProjectUserByUsername(username: String): List<ProjectUser> {
        val user = userService.getUserByUsername(username)
        return getProjectUserByUserId(user.id)
    }
}
