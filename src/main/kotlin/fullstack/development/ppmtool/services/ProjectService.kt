package fullstack.development.ppmtool.services

import fullstack.development.ppmtool.domain.Backlog
import fullstack.development.ppmtool.domain.Project
import fullstack.development.ppmtool.exceptions.ProjectException
import fullstack.development.ppmtool.repositories.BacklogRepository
import fullstack.development.ppmtool.repositories.ProjectRepository
import fullstack.development.ppmtool.repositories.UserRepository
import fullstack.development.ppmtool.web.SecurityController
import org.springframework.stereotype.Service
import java.lang.Exception
@Service
class ProjectService(
  private val projectRepository: ProjectRepository,
  private val backlogRepository: BacklogRepository,
  private val userService: UserService,
  private val securityController: SecurityController,
  private val projectUserService: ProjectUserService
) {
  fun createProject(project: Project): Project {
    val user = userService.getUserByUsername(securityController.currentUserName())
    val newProject = Project(
      projectName = project.projectName,
      projectIdentifier = project.projectIdentifier.toUpperCase(),
      description = project.description,
      startDate = project.startDate,
      endDate = project.endDate,
      projectLeader = user.username
    )
    try {
      val savedProject = projectRepository.save(newProject)
      backlogRepository.save(Backlog(
        projectIdentifier = project.projectIdentifier.toUpperCase(),
        project = savedProject))
      return savedProject
    } catch (e: Exception) {
      if (e.cause !== null && e.cause.toString() == "org.hibernate.exception.DataException: could not execute statement"){
        throw ProjectException("Project Identifier '${project.projectIdentifier} cannot be longer then 5 digits")
      }
      throw ProjectException("Project Id '${project.projectIdentifier}' already exists")
    }
  }

  fun addProject(projectIdentifier: String, projectName: String, description: String, projectLeader: String?): Project {
    val newProject = Project(
      projectName = projectName,
      projectIdentifier = projectIdentifier.toUpperCase(),
      description = description,
      projectLeader = projectLeader
    )
    try {
      val savedProject = projectRepository.save(newProject)
      backlogRepository.save(Backlog(
        projectIdentifier = projectIdentifier.toUpperCase(),
        project = savedProject))
      return savedProject
    } catch (e: Exception) {
      if (e.cause !== null && e.cause.toString() == "org.hibernate.exception.DataException: could not execute statement"){
        throw ProjectException("Project Identifier '${projectIdentifier} cannot be longer then 5 digits")
      }
      throw ProjectException("Project Id '${projectIdentifier}' already exists")
    }
  }

  fun findProjectByIdentifier(identifier: String): Project {
    val project = projectRepository.findProjectByProjectIdentifier(identifier.toUpperCase())
      ?: throw ProjectException("Unable to find project with ID: '$identifier'")
    val projectUsers = projectUserService.getProjectUserByUsername(securityController.currentUserName())
    if (project.id in projectUsers.map { it.projectId } || project.projectLeader == securityController.currentUserName()) {
      return project
    } else throw ProjectException("User ${securityController.currentUserFullName()} does not have access to this Project")
  }

  fun findAllProjects(userName: String): List<Project> {
    val user = userService.getUserByUsername(userName)
    val projectUser = projectUserService.getProjectUserByUserId(user.id)
    return projectRepository
      .findAll()
      .filter { it.id in projectUser.map { pu -> pu.projectId } || it.projectLeader == userName }
  }

  fun findProjectsGQL(): List<Project> = projectRepository.findAll()

  fun deleteProjectByIdentifier(identifier: String, userName: String): Boolean {
    val project = projectRepository.findProjectByProjectIdentifierAndProjectLeader(identifier.toUpperCase(), userName)
      ?: throw ProjectException("Unable to find project with ID: '$identifier'")
    projectRepository.delete(project)
    return true
  }

  fun updateProject(projectId: String, project: Project): Project {
    val existingProject = projectRepository.findProjectByProjectIdentifierAndProjectLeader(projectId, securityController.currentUserName())
      ?: throw ProjectException("Unable to find project with ID: '$projectId'")
    existingProject.apply {
      this.description = project.description
      this.projectName = project.projectName
      this.startDate = project.startDate
      this.endDate = project.endDate
      this.onModified()
    }

    return projectRepository.save(existingProject)
  }

  fun getProjectIdByProjectIdentifier(projectId: String): Long =
    projectRepository.findProjectByProjectIdentifier(projectId)?.id
      ?: throw ProjectException("Unable to find project with ID: $projectId")

}
