package fullstack.development.ppmtool.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.PrePersist
import javax.persistence.PreUpdate

@Entity
data class ProjectTask(
    @Column(updatable = false)
    val projectSequence: String,
    val projectIdentifier: String,
    var summary: String,
    var acceptanceCriteria: String? = null,
    var status: String? = "TO_DO",
    var priority: Int? = 3,
    var dueDate: Date? = null,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id")
    @JsonIgnore
    val backlog: Backlog
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1

    private lateinit var createdAt: Date
    private lateinit var modifiedAt: Date

    @PrePersist
    fun onCreate() {
        this.createdAt = Date()
    }

    @PreUpdate
    fun modifiedAt() {
        this.modifiedAt = Date()
    }
}