package org.github.tarolas.travian.api.data

import java.time.Duration
import java.util.*
import javax.persistence.*

@Entity
class Cookie (
        @Id @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Long? = null,
        val name: String,
        val value: String,
        val httpOnly: Boolean,
        val secure: Boolean,
        val path: String?,
        val domain: String?,
        val maxAge: Duration
) {
    lateinit var creationDate: Date
    lateinit var updateDate: Date

    @PrePersist
    fun onCreate() { creationDate = Date() }

    @PreUpdate
    fun onUpdate()  { updateDate = Date() }
}