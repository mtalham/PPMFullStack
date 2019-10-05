package fullstack.development.ppmtool.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import fullstack.development.ppmtool.services.ProjectUserService
import org.springframework.stereotype.Component

@Component
class RootMutationResolver(private val projectUserService: ProjectUserService): GraphQLMutationResolver {

    fun addUserToProject(username: String, projectId: String): Boolean =
        projectUserService.addUserToProject(username, projectId)

}