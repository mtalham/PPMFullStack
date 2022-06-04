package fullstack.development.ppmtool.graphql

import fullstack.development.ppmtool.domain.Project
import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.services.ProjectService
import fullstack.development.ppmtool.services.UserService
import graphql.kickstart.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

@Component
class RootQueryResolver(private val userService: UserService, private val projectService: ProjectService) :
  GraphQLQueryResolver {
  fun getHello(): String = "Hello from GraphQL!!!"

  fun getUsers(): List<User> = userService.getUsers()
  fun getProjects(): List<Project> = projectService.findProjectsGQL()
}