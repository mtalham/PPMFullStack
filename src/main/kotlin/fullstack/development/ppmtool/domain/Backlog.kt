package fullstack.development.ppmtool.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
data class Backlog(
    val projectIdentifier: String,
    var pSequence: Int? = 0,

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    val project: Project,

    @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.REFRESH], mappedBy = "backlog", orphanRemoval = true)
    val projectTasks: List<ProjectTask>? = ArrayList(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
)