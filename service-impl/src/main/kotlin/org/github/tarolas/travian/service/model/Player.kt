package org.github.tarolas.travian.api.data

import javax.persistence.*

@Entity
class Player(
        @Id @GeneratedValue(strategy = GenerationType.TABLE)
        val id: Long? = null,
        val username: String,
        val server: String,
        val password: String
) {
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    lateinit var cookies: MutableSet<Cookie>
}