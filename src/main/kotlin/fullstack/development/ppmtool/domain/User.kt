package fullstack.development.ppmtool.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

@Entity
data class User(
    @field:NotEmpty(message = "Cannot be empty")
    @field:Email(message = "Invalid email")
    private val username: String,
    val fullName: String,
    private val password: String,
    @field:Transient
    val confirmPassword: String

): UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1

//    User Details methods
    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? = null

    @JsonIgnore
    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = this.username

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = this.password

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean = true

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean = true
}