package org.github.tarolas.travian.service.model

import org.github.tarolas.travian.service.dto.NoArg
import javax.persistence.*

@NoArg
@Entity
class Player(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        var id: Long? = null,
        var username: String,
        var server: String,
        var password: String
) {
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "player_id")
    var cookies: MutableSet<Cookie>? = null
}