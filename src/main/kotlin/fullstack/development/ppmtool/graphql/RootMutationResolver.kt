package fullstack.development.ppmtool.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import fullstack.development.ppmtool.domain.Project
import fullstack.development.ppmtool.services.ProjectService
import fullstack.development.ppmtool.services.ProjectUserService
import org.springframework.stereotype.Component

@Component
class RootMutationResolver(private val projectUserService: ProjectUserService, private val projService: ProjectService) : GraphQLMutationResolver {

  fun addUserToProject(username: String, projectId: String): Boolean =
      projectUserService.addUserToProject(username, projectId)

  fun addProject(projectIdentifier: String, projectName: String, description: String, projectLeader: String?): Project =
      projService.addProject(projectIdentifier, projectName, description, projectLeader)
}