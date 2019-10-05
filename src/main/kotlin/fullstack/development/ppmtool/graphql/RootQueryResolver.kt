package fullstack.development.ppmtool.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import fullstack.development.ppmtool.domain.User
import fullstack.development.ppmtool.services.UserService
import org.springframework.stereotype.Component

@Component
class RootQueryResolver( private val userService: UserService): GraphQLQueryResolver {
    fun getHello(): String = "Hello from GraphQL!!!"

    fun getUsers(): List<User> = userService.getUsers()
}