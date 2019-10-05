package fullstack.development.ppmtool.web

import fullstack.development.ppmtool.domain.InputProjectTask
import fullstack.development.ppmtool.domain.ProjectTask
import fullstack.development.ppmtool.services.ProjectTaskService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
class BacklogController(private val projectTaskService: ProjectTaskService) {
    @PostMapping("/{backlog_id}")
    fun addProjectTaskToBacklog(@RequestBody projectTask: InputProjectTask, @PathVariable backlog_id: String): ProjectTask =
        projectTaskService.addProjectTask(backlog_id.toUpperCase(), projectTask)

    @GetMapping("/{backlog_id}")
    fun getProjectBacklog(@PathVariable backlog_id: String): List<ProjectTask> =
        projectTaskService.getProjectBacklog(backlog_id.toUpperCase())

    @GetMapping("/{backlog_id}/{sequence}")
    fun getProjectTaskBySequence(@PathVariable backlog_id: String, @PathVariable sequence: String): ProjectTask =
        projectTaskService.getProjectTaskByProjectSequence(backlog_id.toUpperCase(), sequence)

    @PatchMapping("/{backlog_id}/{pt_id}")
    fun updateProjectTask(@RequestBody projectTask: InputProjectTask, @PathVariable backlog_id: String, @PathVariable pt_id: String): ProjectTask =
        projectTaskService.updateProjectTask(projectTask, backlog_id.toUpperCase(), pt_id)

    @DeleteMapping("/{backlog_id}/{pt_id}")
    fun deleteProjectTask(@PathVariable backlog_id: String, @PathVariable pt_id: String): Boolean =
        projectTaskService.deleteProjectTask(backlog_id.toUpperCase(), pt_id)
}