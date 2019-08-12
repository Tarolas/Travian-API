package org.github.tarolas.travian.service.model

import org.github.tarolas.travian.service.dto.NoArg
import javax.persistence.*

@NoArg
@Entity
class Player(
        @Id @GeneratedValue(strategy = GenerationType.TABLE)
        var id: Long? = null,
        var username: String,
        var server: String,
        var password: String
) {
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    lateinit var cookies: MutableSet<Cookie>
}