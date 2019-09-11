package org.github.tarolas.travian.service.model

import org.github.tarolas.travian.service.dto.NoArg
import java.time.Duration
import java.util.*
import javax.persistence.*

@NoArg
@Entity
class Cookie(
        @Id @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Long? = null,
        var name: String?,
        var value: String?,
        var httpOnly: Boolean?,
        var secure: Boolean?,
        var path: String?,
        var domain: String?,
        var maxAge: Duration?
) {
    var creationDate: Date? = null
    var updateDate: Date? = null

    @PrePersist
    fun onCreate() {
        creationDate = Date()
    }

    @PreUpdate
    fun onUpdate() {
        updateDate = Date()
    }
}