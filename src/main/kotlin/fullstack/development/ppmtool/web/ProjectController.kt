package fullstack.development.ppmtool.web

import fullstack.development.ppmtool.domain.Project
import fullstack.development.ppmtool.services.ProjectService
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/project")
@CrossOrigin
class ProjectController(private val projectService: ProjectService) {

    @PostMapping("")
    fun createNewProject(@RequestBody project: Project, result: BindingResult): Project =
        projectService.createProject(project)

    @GetMapping("/{projectId}")
    fun getProjectByIdentifier(@PathVariable projectId: String): Project =
        projectService.findProjectByIdentifier(projectId)

    @GetMapping("/all")
    fun findAllProjects(principal: Principal): List<Project> =
        projectService.findAllProjects(principal.name)

    @DeleteMapping("/{projectId}")
    fun deleteProjectByIdentifier(@PathVariable projectId: String, principal: Principal) =
        projectService.deleteProjectByIdentifier(projectId, principal.name)

    @PutMapping("/{projectId}")
    fun updateProject(@PathVariable projectId: String, @RequestBody project: Project) =
        projectService.updateProject(projectId.toUpperCase(), project)
}