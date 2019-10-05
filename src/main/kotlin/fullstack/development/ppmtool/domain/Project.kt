package fullstack.development.ppmtool.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Date
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToOne
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Entity
data class Project(
    @Column(unique = true)
    val projectIdentifier: String,
    var projectName: String,
    var description: String,
    var startDate: Date? = null,
    var endDate: Date? = null,

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], mappedBy = "project")
    @JsonIgnore
    val backlog: Backlog? = null,

    val projectLeader: String? = null,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
) {
    private lateinit var createdAt: Date

    private lateinit var modifiedAt: Date

    @PrePersist
    fun onCreate() {
        this.createdAt = Date()
    }

    @PreUpdate
    fun onModified() {
        this.modifiedAt = Date()
    }
}