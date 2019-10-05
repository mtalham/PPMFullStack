package fullstack.development.ppmtool.services

import fullstack.development.ppmtool.domain.InputProjectTask
import fullstack.development.ppmtool.domain.ProjectTask
import fullstack.development.ppmtool.exceptions.ProjectException
import fullstack.development.ppmtool.repositories.BacklogRepository
import fullstack.development.ppmtool.repositories.ProjectTaskRepository
import org.springframework.stereotype.Service

@Service
class ProjectTaskService(
    private val projectTaskRepository: ProjectTaskRepository,
    private val projectService: ProjectService
) {
    fun addProjectTask(projectIdentifier: String, projectTask: InputProjectTask): ProjectTask {
        //add PTs to a specific project // set the BL to project task. set the relation
        val backlog = projectService.findProjectByIdentifier(projectIdentifier).backlog
            ?: throw ProjectException("Unable to find backlog for project: '$projectIdentifier'")

        backlog.pSequence = backlog.pSequence?.plus(1)
        val newProjectTask = ProjectTask(
            projectIdentifier = backlog.projectIdentifier,
            projectSequence = projectIdentifier + "-" + backlog.pSequence,
            summary = projectTask.summary,
            backlog = backlog,
            priority = projectTask.priority,
            acceptanceCriteria = projectTask.acceptanceCriteria,
            status = projectTask.status,
            dueDate = projectTask.dueDate
        )
        return projectTaskRepository.save(newProjectTask)
    }

    fun getProjectBacklog(backlog_id: String): List<ProjectTask> =
        projectService.findProjectByIdentifier(backlog_id).run {
             projectTaskRepository.findByProjectIdentifierOrderByPriority(backlog_id) ?: emptyList()
        }

    fun getProjectTaskByProjectSequence(backlog_id: String, sequence: String): ProjectTask =
        projectService.findProjectByIdentifier(backlog_id).run {
        val projectTask = projectTaskRepository.findByProjectSequence(sequence)
            ?: throw ProjectException("Project task with project sequence '$sequence' does not exist")
        if (projectTask.projectIdentifier != backlog_id) {
            throw ProjectException("Project Task '$sequence' does not exist in project backlog '$backlog_id'")
        }
        return projectTask
        }

    fun updateProjectTask(projectTask: InputProjectTask, backlog_id: String, sequence: String): ProjectTask {
        val existingTask = getProjectTaskByProjectSequence(backlog_id, sequence)
        existingTask.apply {
            summary = projectTask.summary
            priority = projectTask.priority
            acceptanceCriteria = projectTask.acceptanceCriteria
            status = projectTask.status
            dueDate = projectTask.dueDate
        }
        return projectTaskRepository.save(existingTask)
    }

    fun deleteProjectTask(backlog_id: String, pt_id: String): Boolean {
        val existingTask = getProjectTaskByProjectSequence(backlog_id, pt_id)
        projectTaskRepository.delete(existingTask)
        return true
    }
}

